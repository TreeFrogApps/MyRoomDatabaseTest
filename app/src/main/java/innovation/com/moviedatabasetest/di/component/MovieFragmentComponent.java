package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragmentList;
import innovation.com.moviedatabasetest.di.module.MovieFragmentModule;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;

@FragmentScope
@Subcomponent(modules = MovieFragmentModule.class)
public interface MovieFragmentComponent {

    void inject(MovieFragmentList fragment);
}
