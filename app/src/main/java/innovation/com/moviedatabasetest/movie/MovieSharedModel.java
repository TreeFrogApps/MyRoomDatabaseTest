package innovation.com.moviedatabasetest.movie;


import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MovieSharedModel implements IMovieSharedModel {

    private final Context context;

    public MovieSharedModel(Context context) {
        this.context = context;
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
