package innovation.com.moviedatabasetest.movie.fragmentsearch;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import innovation.com.moviedatabasetest.provider.db.Movie;

public interface IMovieFragmentSearchPresenter extends IPresenterLifeCycle<IMovieFragmentSearchView> {

    void subscribeToSearchEvents();

    void setupView(RecyclerView resultsRecyclerView, List<Movie> resultsList, Context context);
}
