package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.di.module.MovieFragmentModule;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragment;

@FragmentScope
@Subcomponent(modules = MovieFragmentModule.class)
public interface MovieFragmentComponent {

    void inject(MovieFragment fragment);
}
