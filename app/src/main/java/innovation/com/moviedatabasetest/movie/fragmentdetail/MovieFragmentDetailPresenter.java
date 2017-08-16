package innovation.com.moviedatabasetest.movie.fragmentdetail;


import android.support.v4.app.FragmentManager;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import retrofit2.Retrofit;

public class MovieFragmentDetailPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentDetailPresenter {

    private static final String TAG = MovieFragmentDetailPresenter.class.getSimpleName();

    private final IMovieSharedModel model;
    private final FragmentManager manager;

    public MovieFragmentDetailPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentDetailView iMovieFragmentDetailView, Unbinder unbinder) {

    }

    @Override public void unbind(boolean isChangingConfigurations) {

    }

    @Override public void unbindView() {

    }

    @Override public void movieDetailRequest(String movieId) {

    }
}
