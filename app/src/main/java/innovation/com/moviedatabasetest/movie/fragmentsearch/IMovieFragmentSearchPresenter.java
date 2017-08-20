package innovation.com.moviedatabasetest.movie.fragmentsearch;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import innovation.com.moviedatabasetest.common.MovieRecyclerAdapter;

public interface IMovieFragmentSearchPresenter extends IPresenterLifeCycle<IMovieFragmentSearchView> {

    void subscribeToSearchEvents();

    void setupView(RecyclerView resultsRecyclerView, MovieRecyclerAdapter adapter, Context context);
}
