package innovation.com.moviedatabasetest.movie.fragmentsearch;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import innovation.com.moviedatabasetest.common.MovieRecyclerAdapter;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieFragmentSearchPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentSearchPresenter {

    private static final String TAG = MovieFragmentSearchPresenter.class.getSimpleName();
    private final IMovieSharedModel model;
    private final FragmentManager manager;

    private IMovieFragmentSearchView view;
    private Unbinder unbinder;
    private CompositeDisposable searchDisposable;

    public MovieFragmentSearchPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
        searchDisposable = new CompositeDisposable();
    }

    @Override public void bind(IMovieFragmentSearchView iMovieFragmentSearchView, Unbinder unbinder) {
        this.view = iMovieFragmentSearchView;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {
        if (searchDisposable != null && !searchDisposable.isDisposed()) searchDisposable.dispose();
    }

    @Override public void unbindView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override public void subscribeToSearchEvents() {
        searchDisposable.add(model.subscribeToSearchResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::searchResults, this::onError));
    }

    @Override public void setupView(RecyclerView resultsRecyclerView, List<Movie> resultsList, Context context) {
        final MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(resultsList);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        resultsRecyclerView.setAdapter(adapter);

        searchDisposable.add(adapter.updateFavouriteObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(model::insertMovie, this::onError));
    }

    private void searchResults(List<Movie> movieList) {
        view.searchResults(movieList);
    }

    private void onError(Throwable e) {
        Log.e(TAG, "Error searching : " + e);
    }
}
