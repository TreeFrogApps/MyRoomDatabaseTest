package innovation.com.moviedatabasetest.di.component;


import dagger.Component;
import innovation.com.moviedatabasetest.di.module.MovieActivityModule;
import innovation.com.moviedatabasetest.di.module.MovieAppModule;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;

@ApplicationScope
@Component(modules = MovieAppModule.class)
public interface MovieAppComponent {

    MovieActivityComponent addRoomActivityComponent(MovieActivityModule module);
}
