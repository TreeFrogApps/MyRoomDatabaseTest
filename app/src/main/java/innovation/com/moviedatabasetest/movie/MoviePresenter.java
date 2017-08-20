package innovation.com.moviedatabasetest.movie;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.widget.FrameLayout;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetailView;
import innovation.com.moviedatabasetest.movie.fragmentpager.MovieFragment;
import innovation.com.moviedatabasetest.movie.fragmentsearch.MovieFragmentSearchView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MoviePresenter extends GenericPresenter<IMovieSharedModel> implements IMoviePresenter {

    private final Context context;
    private final IMovieSharedModel model;
    private final FragmentManager manager;
    private FrameLayout searchFrameLayout;

    @Nullable private Unbinder unbinder;

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


    @Override public void setupView(FrameLayout searchLayout, @IdRes int movieContainer, @IdRes int searchContainer, @IdRes int detailContainer, boolean dualPane) {
        model.setDualPane(dualPane);
        this.searchFrameLayout = searchLayout;
        final MovieFragmentSearchView fragmentSearch = (MovieFragmentSearchView) manager.findFragmentByTag(MovieFragmentSearchView.class.getSimpleName());
        final MovieFragment fragmentList = (MovieFragment) manager.findFragmentByTag(MovieFragment.class.getSimpleName());
        final MovieFragmentDetailView fragmentDetail = (MovieFragmentDetailView) manager.findFragmentByTag(MovieFragmentDetailView.class.getSimpleName());

        setupSearchFragment(searchContainer, fragmentSearch);
        setupFragmentViews(detailContainer, movieContainer, fragmentDetail, fragmentList, dualPane);
        if (model.isSearching()) {
            searchLayout.setVisibility(VISIBLE);
        }
    }

    @Override public void openSearchFragment() {
        model.setSearching(true);
        searchFrameLayout.setVisibility(VISIBLE);
    }

    @Override public boolean closeSearchFragment(SearchView searchView) {
        model.setSearching(false);
        searchView.onActionViewCollapsed();
        searchFrameLayout.setVisibility(GONE);
        return true;
    }

    @Override public void performSearchQuery(String query) {
        if (isValidSearch(query)) {
            model.performMovieSearch(query);
        }
    }

    @Override public boolean backPressed() {
        return searchFrameLayout.getVisibility() == VISIBLE;
    }

    private boolean isValidSearch(String query) {
        return query != null && query.length() > 1 && query.matches(".*[a-zA-Z0-9]+.*");
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
}
