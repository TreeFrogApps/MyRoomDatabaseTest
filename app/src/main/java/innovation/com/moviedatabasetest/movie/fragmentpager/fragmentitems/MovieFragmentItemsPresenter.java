package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailView;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieFragmentItemsPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentItemsPresenter {

    private static final String TAG = MovieFragmentItemsPresenter.class.getSimpleName();

    private final IMovieSharedModel model;
    private final FragmentManager manager;
    private IMovieFragmentItems view;
    private Unbinder unbinder;
    private CompositeDisposable movieDisposable;

    public MovieFragmentItemsPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentItems iMovieFragmentItems, Unbinder unbinder) {
        this.view = iMovieFragmentItems;
        this.unbinder = unbinder;
        this.movieDisposable = new CompositeDisposable();
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

        movieDisposable.add(adapter.updateFavouriteObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(model::updateMovie, this::onError));

        movieDisposable.add(model.getMovieList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNextMovies, this::onError));

        movieDisposable.add(adapter.movieClickedObservable()
                .subscribe(this::openDetailFragment, this::onError));
    }

    @Override public Single<Movie> getMovie(long movieId) {
        return Single.defer(() -> model.getMovie(movieId));
    }

    private void onNextMovies(List<Movie> movieList) {
        view.movieList(movieList);
    }

    private void onError(Throwable e) {
        Log.e(TAG, "Error : " + e);
    }

    private void openDetailFragment(Movie movie) {
        model.updateDetailMovie(movie);
        final String tag = MovieFragmentDetailView.class.getSimpleName();
        MovieFragmentDetailView fragment = (MovieFragmentDetailView) manager.findFragmentByTag(tag);

        if (fragment == null) {
            fragment = MovieFragmentDetailView.newInstance(null);
            if (model.isDualPane()) {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_slide_enter, R.anim.fragment_fade_exit)
                        .replace(R.id.movieDetailContainer, fragment, tag).commit();
            } else {
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_slide_enter, R.anim.fragment_fade_exit)
                        .replace(R.id.movieFragmentContainer, fragment, tag).addToBackStack(null).commit();
            }
        }
    }

}

