package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import innovation.com.moviedatabasetest.common.MovieRecyclerAdapter;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Single;

public interface IMovieFragmentItemsPresenter extends IPresenterLifeCycle<IMovieFragmentItems> {

    void updateMovie(Movie movie);

    void setupView(Context context, RecyclerView movieRecycleView, MovieRecyclerAdapter adapter, int id);

    Single<Movie> getMovie(long movieId);

}
