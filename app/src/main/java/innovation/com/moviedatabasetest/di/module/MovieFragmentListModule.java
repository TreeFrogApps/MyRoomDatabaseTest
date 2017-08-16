package innovation.com.moviedatabasetest.di.module;


import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.di.scope.FragmentScope;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentdetail.IMovieFragmentDetailPresenter;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailPresenter;
import innovation.com.moviedatabasetest.movie.fragmentlist.IMovieFragmentListPresenter;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragmentListPresenter;
import retrofit2.Retrofit;

@Module public class MovieFragmentListModule {

    @Provides @FragmentScope IMovieFragmentListPresenter providePresenter(IMovieSharedModel model, FragmentManager manager) {
        return new MovieFragmentListPresenter(model, manager);
    }
}
