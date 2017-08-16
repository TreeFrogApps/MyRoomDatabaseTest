package innovation.com.moviedatabasetest.movie.fragmentlist;


import java.util.List;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import io.reactivex.Observable;

public interface IMovieFragmentListPresenter extends IPresenterLifeCycle<IMovieFragmentListView> {

    Observable<List<String>> getMovieList();
}
