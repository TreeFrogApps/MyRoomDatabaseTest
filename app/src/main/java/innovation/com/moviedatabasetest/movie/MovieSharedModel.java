package innovation.com.moviedatabasetest.movie;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import innovation.com.moviedatabasetest.common.PreferenceConstants;
import innovation.com.moviedatabasetest.provider.MovieProvider;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
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

    @Override public Single<Movie> getMovieInfo(String movie) {
        return Single.defer(() -> movieProvider.getMovieInfo(movie))
                .subscribeOn(Schedulers.io());
    }

    @Override public Flowable<List<Movie>> getMovieList() {
        long lastUpdate = preferences.getLong(PreferenceConstants.MOVIES_LAST_API_UPDATE, 0L);
        return Flowable.defer(() -> movieProvider.getMovieList(lastUpdate))
                .subscribeOn(Schedulers.io());
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
}
