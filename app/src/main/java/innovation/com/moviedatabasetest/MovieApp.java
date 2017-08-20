package innovation.com.moviedatabasetest;

import android.app.Application;
import android.content.Context;

import innovation.com.moviedatabasetest.di.component.DaggerMovieAppComponent;
import innovation.com.moviedatabasetest.di.component.MovieAppComponent;
import innovation.com.moviedatabasetest.di.module.MovieAppModule;


public class MovieApp extends Application {

    private MovieAppComponent movieAppComponent;

    @Override public void onCreate() {
        super.onCreate();

        movieAppComponent = createComponent();
    }

    protected MovieAppComponent createComponent() {
        return DaggerMovieAppComponent.builder().movieAppModule(new MovieAppModule(this)).build();
    }

    public static MovieAppComponent getRoomAppComponent(Context context) {
        return ((MovieApp) context.getApplicationContext()).movieAppComponent;
    }
}
