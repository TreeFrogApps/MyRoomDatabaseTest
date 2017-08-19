package innovation.com.moviedatabasetest.movie;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import innovation.com.moviedatabasetest.common.PreferenceConstants;
import innovation.com.moviedatabasetest.provider.MovieProvider;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MovieSharedModel implements IMovieSharedModel {

    private final Context context;
    private final MovieProvider movieProvider;
    private final SharedPreferences preferences;

    private PublishSubject<String> searchSubject;

    public MovieSharedModel(Context context, MovieProvider movieProvider, SharedPreferences preferences) {
        this.context = context;
        this.movieProvider = movieProvider;
        this.preferences = preferences;
        searchSubject = PublishSubject.create();
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

    @Override public Flowable<List<Movie>> subscribeToSearchResults() {
        return searchSubject.debounce(400, MILLISECONDS)
                .switchMapSingle(movieProvider::performMovieSearch)
                .toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io());
    }

    @Override public void updateMovie(Movie movie) {
        movieProvider.updateMovie(movie);
    }
}
