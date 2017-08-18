package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import innovation.com.moviedatabasetest.movie.fragmentpager.IMovieFragment;
import innovation.com.moviedatabasetest.movie.fragmentpager.MoviePagerAdapter;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMovieFragmentItemsPresenter extends IPresenterLifeCycle<IMovieFragmentItems> {

    void updateMovie(Movie movie);

    void setupView(Context context, RecyclerView movieRecycleView,  MovieRecyclerAdapter adapter, int id);

    Single<Movie> getMovie(long movieId);

}
