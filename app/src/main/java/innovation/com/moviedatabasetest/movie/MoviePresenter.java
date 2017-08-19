package innovation.com.moviedatabasetest.movie;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailView;
import innovation.com.moviedatabasetest.movie.fragmentpager.MovieFragment;
import innovation.com.moviedatabasetest.movie.fragmentsearch.MovieFragmentSearchView;


public class MoviePresenter extends GenericPresenter<IMovieSharedModel> implements IMoviePresenter {

    private final Context context;
    private final IMovieSharedModel model;
    private final FragmentManager manager;

    @Nullable private Unbinder unbinder;
    private boolean dualPane;

    public MoviePresenter(Context context, IMovieSharedModel model, FragmentManager manager) {
        this.context = context;
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieView iMovieView, Unbinder unbinder) {
        this.unbinder = unbinder;
        model.updateMovies();
    }

    @Override public void unbind(boolean isChangingConfigurations) {
    }

    @Override public void unbindView() {
        if (unbinder != null) unbinder.unbind();
    }


    @Override public void setupView(@IdRes int movieContainer, @IdRes int searchContainer, @IdRes int detailContainer, boolean dualPane) {
        model.setDualPane(dualPane);
        final MovieFragmentSearchView fragmentSearch = (MovieFragmentSearchView) manager.findFragmentByTag(MovieFragmentSearchView.class.getSimpleName());
        final MovieFragment fragmentList = (MovieFragment) manager.findFragmentByTag(MovieFragment.class.getSimpleName());
        final MovieFragmentDetailView fragmentDetail = (MovieFragmentDetailView) manager.findFragmentByTag(MovieFragmentDetailView.class.getSimpleName());

        setupSearchFragment(searchContainer, fragmentSearch);
        setupFragmentViews(detailContainer, movieContainer, fragmentDetail, fragmentList, dualPane);
    }

    private void setupSearchFragment(@IdRes int searchContainer, MovieFragmentSearchView fragmentSearch) {
        if (fragmentSearch == null) {
            manager.beginTransaction().add(searchContainer,
                    MovieFragmentSearchView.newInstance(null), MovieFragmentSearchView.class.getSimpleName()).commit();
        }
    }

    private void setupFragmentViews(@IdRes int detailContainer, @IdRes int movieContainer,
                                    MovieFragmentDetailView fragmentDetail, MovieFragment movieFragment, boolean dualPane) {

        if (movieFragment == null) {
            manager.beginTransaction().add(movieContainer, MovieFragment.newInstance(null), MovieFragment.class.getSimpleName()).commitNow();
        }

        if (fragmentDetail != null) {
            manager.popBackStackImmediate();
            manager.beginTransaction().remove(fragmentDetail).commitNow();
            if (dualPane) {
                manager.beginTransaction()
                        .replace(detailContainer, fragmentDetail, MovieFragmentDetailView.class.getSimpleName())
                        .commit();
            } else {
                manager.beginTransaction()
                        .replace(movieContainer, fragmentDetail, MovieFragmentDetailView.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }


    // TODO - on click in single pane (observe events from common model) - replace with detail fragment, addTransaction to back stack ...
}
