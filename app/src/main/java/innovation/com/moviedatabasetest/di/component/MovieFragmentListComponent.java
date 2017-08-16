package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.di.module.MovieFragmentListModule;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragmentListView;

@FragmentScope
@Subcomponent(modules = MovieFragmentListModule.class)
public interface MovieFragmentListComponent {

    void inject(MovieFragmentListView fragment);
}
