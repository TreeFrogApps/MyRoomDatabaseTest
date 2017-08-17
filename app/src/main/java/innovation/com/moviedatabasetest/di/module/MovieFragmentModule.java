package innovation.com.moviedatabasetest.di.module;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.base.GenericActivity;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentlist.IMovieFragmentPresenter;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragmentPresenter;
import innovation.com.moviedatabasetest.movie.fragmentlist.MoviePagerAdapter;

@Module public class MovieFragmentModule {

    private Fragment fragment;

    public MovieFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides @FragmentScope IMovieFragmentPresenter providePresenter(IMovieSharedModel model, FragmentManager manager) {
        return new MovieFragmentPresenter(model, manager);
    }

    @Provides @FragmentScope MoviePagerAdapter providePagerAdapter(Context context){
        return new MoviePagerAdapter(context, fragment.getChildFragmentManager());
    }
}
