package innovation.com.moviedatabasetest.di.module;


import android.content.Context;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.base.GenericActivity;
import innovation.com.moviedatabasetest.di.scope.ActivityScope;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.movie.MVP.IMovieModel;
import innovation.com.moviedatabasetest.movie.MovieSharedModel;
import innovation.com.moviedatabasetest.movie.MoviePresenter;

import static innovation.com.moviedatabasetest.movie.MVP.IMoviePresenter;

@Module public class MovieActivityModule {

    private GenericActivity activity;

    public MovieActivityModule(GenericActivity activity) {
        this.activity = activity;
    }

    @Provides @ActivityScope FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides @ActivityScope IMoviePresenter providePresenter(Context context, IMovieModel model, FragmentManager manager) {
        return new MoviePresenter(context, model, manager);
    }

    @Module public static class AppScopeModule {

        @Provides @ApplicationScope IMovieModel provideModel(Context context) {
            return new MovieSharedModel(context);
        }
    }
}
