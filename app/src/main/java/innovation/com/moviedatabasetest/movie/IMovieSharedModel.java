package innovation.com.moviedatabasetest.movie;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public interface IMovieSharedModel {

    Observable<String> getMovieInfo(String movie);

    Observable<List<Object>> getMovieList();

    Observable<List<Object>> performMovieSearch(String query);

    PublishSubject<String> getSearchSubject();

}
