package innovation.com.moviedatabasetest.movie;


import android.content.Context;

import java.util.List;

import innovation.com.moviedatabasetest.api.MovieApiManager;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MovieSharedModel implements IMovieSharedModel {

    private final Context context;
    private final MovieApiManager apiManager;

    public MovieSharedModel(Context context, MovieApiManager apiManager) {
        this.context = context;
        this.apiManager = apiManager;
    }

    @Override public Observable<String> getMovieInfo(String movie) {
        return null;
    }

    @Override public Observable<List<Object>> getMovieList() {
        return null;
    }

    @Override public Observable<List<Object>> performMovieSearch(String query) {
        return null;
    }

    @Override public PublishSubject<String> getSearchSubject() {
        return null;
    }
}
