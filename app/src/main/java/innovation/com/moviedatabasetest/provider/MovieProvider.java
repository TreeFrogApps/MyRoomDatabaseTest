package innovation.com.moviedatabasetest.provider;


import android.content.SharedPreferences;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.common.Date;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.api.ApiManager;
import innovation.com.moviedatabasetest.provider.db.DBDelegate;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static innovation.com.moviedatabasetest.common.PreferenceConstants.MOVIES_LAST_API_UPDATE;
import static java.util.concurrent.TimeUnit.DAYS;

@ApplicationScope public final class MovieProvider {

    private static final String TAG = MovieProvider.class.getSimpleName();
    private static final long ONE_WEEK = DAYS.toMillis(7);

    private final DBDelegate dBManager;
    private final ApiManager apiManager;
    private final Date date;

    @Inject MovieProvider(DBDelegate dBManager, ApiManager apiManager, Date date) {
        this.dBManager = dBManager;
        this.apiManager = apiManager;
        this.date = date;
    }

    public Single<Movie> getMovie(long rowId) {
        return Single.defer(() -> dBManager.getByRowId(rowId));
    }

    public void updateMovies(SharedPreferences preferences, long lastUpdate) {
        if ((lastUpdate + ONE_WEEK) < date.now()) {
            Flowable.zip(updateInCinemas(), updatePopular(),
                    (movieList, movieList2) -> {
                        movieList.addAll(movieList2);
                        return movieList;
                    })
                    .subscribeOn(Schedulers.io())
                    .doOnComplete(() -> preferences.edit()
                            .putLong(MOVIES_LAST_API_UPDATE, date.now()).commit())
                    .subscribe(this::updateDatabase, this::onError);
        }
    }

    public Flowable<List<Movie>> getInCinemas() {
        return dBManager.getByMovieType(Movie.MovieType.IN_CINEMAS)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<Movie>> getPopular() {
        return dBManager.getByMovieType(Movie.MovieType.POPULAR)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<Movie>> getFavourites() {
        return dBManager.getFavourites()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<Movie>> performMovieSearch(String query) {
        return apiManager.searchForMovies(query)
                .subscribeOn(Schedulers.io());
    }

    public void updateMovie(Movie movie) {
        dBManager.updateMovies(Collections.singletonList(movie));
    }

    public void insertMovie(Movie movie){
        dBManager.insert(movie);
    }

    private Flowable<List<Movie>> updateInCinemas() {
        return apiManager.getInCinemasList()
                .map(movieList -> {
                    for (Movie m : movieList) {
                        m.movieType = Movie.MovieType.IN_CINEMAS.name();
                    }
                    return movieList;
                });
    }

    private Flowable<List<Movie>> updatePopular() {
        return apiManager.getPopularList()
                .map(movieList -> {
                    for (Movie m : movieList) {
                        m.movieType = Movie.MovieType.POPULAR.name();
                    }
                    return movieList;
                });
    }

    private void updateDatabase(List<Movie> movieList) {
        dBManager.deleteNonFavourites();
        dBManager.insertMulti(movieList);
    }

    private void onError(Throwable e) {
        String message = "Error getting movies from Api";
        if(e instanceof HttpException){
            message += " - Http Code : " + ((HttpException) e).code();
        }
        Log.e(TAG, message);
    }
}
