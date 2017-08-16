package innovation.com.moviedatabasetest.di.module;


import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailPresenter;
import retrofit2.Retrofit;

import innovation.com.moviedatabasetest.movie.fragmentdetail.IMovieFragmentDetailPresenter;

@Module public class MovieFragmentDetailModule {

    @Provides @FragmentScope IMovieFragmentDetailPresenter providePresenter(IMovieSharedModel model, FragmentManager manager) {
        return new MovieFragmentDetailPresenter(model, manager);
    }
}
