package innovation.com.moviedatabasetest.di.module;


import android.content.Context;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.base.GenericActivity;
import innovation.com.moviedatabasetest.di.scope.ActivityScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.MoviePresenter;

import innovation.com.moviedatabasetest.movie.IMoviePresenter;

@Module public class MovieActivityModule {

    private GenericActivity activity;

    public MovieActivityModule(GenericActivity activity) {
        this.activity = activity;
    }

    @Provides @ActivityScope FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides @ActivityScope IMoviePresenter providePresenter(Context context, IMovieSharedModel model, FragmentManager manager) {
        return new MoviePresenter(context, model, manager);
    }
}
