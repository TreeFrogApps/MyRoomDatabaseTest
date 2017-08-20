package innovation.com.moviedatabasetest.movie;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import innovation.com.moviedatabasetest.common.PreferenceConstants;
import innovation.com.moviedatabasetest.provider.MovieProvider;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MovieSharedModel implements IMovieSharedModel {

    private final Context context;
    private final MovieProvider movieProvider;
    private final SharedPreferences preferences;

    private BehaviorSubject<String> searchSubject;
    private BehaviorSubject<Movie> detailMovieSubject;
    private boolean isDualPane;
    private boolean isSearching;
    private String searchText;

    public MovieSharedModel(Context context, MovieProvider movieProvider, SharedPreferences preferences) {
        this.context = context;
        this.movieProvider = movieProvider;
        this.preferences = preferences;
        searchSubject = BehaviorSubject.create();
        detailMovieSubject = BehaviorSubject.create();
    }

    @Override public Single<Movie> getMovie(long rowId) {
        return Single.defer(() -> movieProvider.getMovie(rowId))
                .subscribeOn(Schedulers.io());
    }

    @Override public Flowable<List<Movie>> getMovieList(int id) {
        final Flowable<List<Movie>> moviesFlowable;
        switch (id) {
            case 0:
                moviesFlowable = movieProvider.getPopular();
                break;
            case 1:
                moviesFlowable = movieProvider.getInCinemas();
                break;
            default:
                moviesFlowable = movieProvider.getFavourites();
                break;
        }
        return moviesFlowable.observeOn(AndroidSchedulers.mainThread());
    }


    @Override public void updateMovies() {
        long lastUpdate = preferences.getLong(PreferenceConstants.MOVIES_LAST_API_UPDATE, 0L);
        movieProvider.updateMovies(preferences, lastUpdate);
    }

    @Override public void performMovieSearch(String query) {
        searchSubject.onNext(query);
    }

    @Override public Observable<List<Movie>> subscribeToSearchResults() {
        return searchSubject.debounce(800, MILLISECONDS)
                .doOnNext(s -> searchText = s)
                .switchMap(s -> movieProvider.performMovieSearch(s).toObservable())
                .subscribeOn(Schedulers.io());
    }

    @Override public synchronized String getSearchText() {
        return searchText;
    }

    @Override public void updateMovie(Movie movie) {
        movieProvider.updateMovie(movie);
    }

    @Override public void insertMovie(Movie movie) {
        movieProvider.insertMovie(movie);
    }

    @Override public Observable<Movie> subscribeDetailMovie() {
        return detailMovieSubject;
    }

    @Override public void updateDetailMovie(Movie movie) {
        detailMovieSubject.onNext(movie);
    }

    @Override public void setDualPane(boolean isDualPane) {
        this.isDualPane = isDualPane;
    }

    @Override public boolean isDualPane() {
        return isDualPane;
    }

    @Override public boolean isSearching() {
        return isSearching;
    }

    @Override public void setSearching(boolean isSearching) {
        this.isSearching = isSearching;
    }
}
