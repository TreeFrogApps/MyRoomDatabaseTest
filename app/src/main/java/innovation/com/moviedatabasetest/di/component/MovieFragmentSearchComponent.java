package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.di.module.MovieFragmentSearchModule;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.fragmentsearch.MovieFragmentSearchView;

@FragmentScope
@Subcomponent(modules = MovieFragmentSearchModule.class)
public interface MovieFragmentSearchComponent {

    void inject(MovieFragmentSearchView fragment);
}
