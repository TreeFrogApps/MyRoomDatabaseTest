package innovation.com.moviedatabasetest.di.module;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentpager.IMovieFragmentPresenter;
import innovation.com.moviedatabasetest.movie.fragmentpager.MovieFragmentPresenter;
import innovation.com.moviedatabasetest.movie.fragmentpager.MoviePagerAdapter;
import innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems.IMovieFragmentItemsPresenter;
import innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems.MovieFragmentItemsPresenter;
import innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems.MovieRecyclerAdapter;

@Module public class MovieFragmentModule {

    private Fragment fragment;

    public MovieFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides @FragmentScope IMovieFragmentPresenter providePresenter(IMovieSharedModel model, FragmentManager manager) {
        return new MovieFragmentPresenter(manager);
    }

    @Provides @FragmentScope IMovieFragmentItemsPresenter provideItemsPresenter(IMovieSharedModel model, FragmentManager manager) {
        return new MovieFragmentItemsPresenter(model, manager);
    }

    @Provides @FragmentScope MoviePagerAdapter providePagerAdapter(Context context){
        return new MoviePagerAdapter(context, fragment.getChildFragmentManager());
    }
}
