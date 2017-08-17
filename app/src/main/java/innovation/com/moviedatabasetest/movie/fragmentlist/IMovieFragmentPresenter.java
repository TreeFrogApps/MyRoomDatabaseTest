package innovation.com.moviedatabasetest.movie.fragmentlist;


import java.util.List;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import io.reactivex.Observable;

public interface IMovieFragmentPresenter extends IPresenterLifeCycle<IMovieFragment> {

    Observable<List<String>> getMovieList();
}
