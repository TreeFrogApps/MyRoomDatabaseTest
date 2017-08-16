package innovation.com.moviedatabasetest.di.component;


import dagger.Subcomponent;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailView;
import innovation.com.moviedatabasetest.di.module.MovieFragmentDetailModule;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;

@FragmentScope
@Subcomponent(modules = MovieFragmentDetailModule.class)
public interface MovieFragmentDetailComponent {

    void inject(MovieFragmentDetailView fragment);
}
