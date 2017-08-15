package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.movie.MovieActivity;
import innovation.com.moviedatabasetest.di.module.MovieActivityModule;
import innovation.com.moviedatabasetest.di.module.MovieFragmentModule;
import innovation.com.moviedatabasetest.di.scope.ActivityScope;

@ActivityScope
@Subcomponent(modules = MovieActivityModule.class)
public interface MovieActivityComponent {

    void inject(MovieActivity movieActivity);

    MovieFragmentComponent addRoomFragmentComponent(MovieFragmentModule fragmentModule);
}
