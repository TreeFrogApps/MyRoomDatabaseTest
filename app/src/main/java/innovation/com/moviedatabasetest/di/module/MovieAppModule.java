package innovation.com.moviedatabasetest.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.MovieApp;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.MovieSharedModel;

@Module(includes = {
        MovieNetModule.class})
public class MovieAppModule {

    private MovieApp app;

    public MovieAppModule(MovieApp app) {
        this.app = app;
    }

    @Provides @ApplicationScope Context getAppContext() {
        return app;
    }

    @Provides @ApplicationScope IMovieSharedModel provideModel(Context context) {
        return new MovieSharedModel(context);
    }
}
