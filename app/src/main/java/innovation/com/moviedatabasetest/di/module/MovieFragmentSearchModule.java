package innovation.com.moviedatabasetest.di.module;


import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentsearch.IMovieFragmentSearchPresenter;
import innovation.com.moviedatabasetest.movie.fragmentsearch.MovieFragmentSearchPresenter;

@Module public class MovieFragmentSearchModule {

    @Provides @FragmentScope IMovieFragmentSearchPresenter providePresenter(IMovieSharedModel model, FragmentManager manager){
        return new MovieFragmentSearchPresenter(model, manager);
    }
}
