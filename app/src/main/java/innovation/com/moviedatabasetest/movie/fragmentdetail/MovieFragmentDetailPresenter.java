package innovation.com.moviedatabasetest.movie.fragmentdetail;


import android.support.v4.app.FragmentManager;
import android.util.Log;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.disposables.Disposable;

public class MovieFragmentDetailPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentDetailPresenter {

    private static final String TAG = MovieFragmentDetailPresenter.class.getSimpleName();

    private final IMovieSharedModel model;
    private final FragmentManager manager;
    private Unbinder unbinder;
    private IMovieFragmentDetailView view;
    private Disposable disposable;

    public MovieFragmentDetailPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentDetailView iMovieFragmentDetailView, Unbinder unbinder) {
        this.view = iMovieFragmentDetailView;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }

    @Override public void unbindView() {
        if (unbinder != null) unbinder.unbind();
    }

    @Override public void subscribeToUpdates() {
        disposable = model.subscribeDetailMovie()
                .subscribe(this::updateView, this::onError);
    }

    private void updateView(Movie movie) {
        view.updateMovieDetails(movie);
    }

    private void onError(Throwable e) {
        Log.e(TAG, "onError : " + e);
    }
}
