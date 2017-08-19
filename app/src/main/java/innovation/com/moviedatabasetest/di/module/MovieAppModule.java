package innovation.com.moviedatabasetest.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.MovieApp;
import innovation.com.moviedatabasetest.common.Date;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.MovieSharedModel;
import innovation.com.moviedatabasetest.provider.MovieProvider;

@Module(includes = {
        MovieNetModule.class,
        MovieDatabaseModule.class})
public class MovieAppModule {

    private MovieApp app;
    private static final String PREFERENCES_NAME = "movie_prefs";

    public MovieAppModule(MovieApp app) {
        this.app = app;
    }

    @Provides @ApplicationScope Context getAppContext() {
        return app;
    }

    @Provides @ApplicationScope SharedPreferences providePreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides @ApplicationScope Date provideDate(){
        return System::currentTimeMillis;
    }

    @Provides @ApplicationScope IMovieSharedModel provideModel(Context context, MovieProvider movieProvider, SharedPreferences preferences) {
        return new MovieSharedModel(context, movieProvider, preferences);
    }

    @Provides @ApplicationScope Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }
}
