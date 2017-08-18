package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MovieFragmentItemsPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentItemsPresenter {

    private static final String TAG = MovieFragmentItemsPresenter.class.getSimpleName();

    private final IMovieSharedModel model;
    private final FragmentManager manager;
    private IMovieFragmentItems view;
    private Unbinder unbinder;
    private Disposable movieDisposable;

    public MovieFragmentItemsPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentItems iMovieFragmentItems, Unbinder unbinder) {
        this.view = iMovieFragmentItems;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {
        if (movieDisposable != null && !movieDisposable.isDisposed()) movieDisposable.dispose();
    }

    @Override public void unbindView() {
        if (unbinder != null) unbinder.unbind();
    }

    @Override public void updateMovie(Movie movie) {
        model.updateMovie(movie);
    }

    @Override public void setupView(Context context, RecyclerView movieRecycleView, MovieRecyclerAdapter adapter, int id) {
        movieRecycleView.setLayoutManager(new LinearLayoutManager(context));
        movieRecycleView.setAdapter(adapter);
        movieDisposable = model.getMovieList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNextMovies, this::onErrorGettingMovieList);
    }

    @Override public Single<Movie> getMovie(long movieId) {
        return Single.defer(() -> model.getMovie(movieId));
    }

    private void onNextMovies(List<Movie> movieList) {
        view.movieList(movieList);
    }

    private void onErrorGettingMovieList(Throwable e) {
        Log.e(TAG, "Error getting movies : " + e);
    }
}
