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
        this.dualPane = dualPane;
        final MovieFragmentSearchView fragmentSearch = (MovieFragmentSearchView) manager.findFragmentByTag(MovieFragmentSearchView.class.getSimpleName());
        final MovieFragmentDetailView fragmentDetail = (MovieFragmentDetailView) manager.findFragmentByTag(MovieFragmentDetailView.class.getSimpleName());
        final MovieFragment fragmentList = (MovieFragment) manager.findFragmentByTag(MovieFragment.class.getSimpleName());

        setupSearchFragment(searchContainer, fragmentSearch);

        if(dualPane){
            setupDetailFragment(detailContainer, fragmentDetail);
            setupListFragment(movieContainer, fragmentList);
        } else {
            setupListFragment(movieContainer, fragmentList);
        }
    }

    private void setupDetailFragment(@IdRes int detailContainer, MovieFragmentDetailView fragmentDetail) {
        if(fragmentDetail == null){
            manager.beginTransaction().add(detailContainer,
                    MovieFragmentDetailView.newInstance(null), MovieFragmentDetailView.class.getSimpleName()).commit();
        }
    }

    private void setupListFragment(@IdRes int movieContainer, MovieFragment fragmentList) {
        if(fragmentList == null){
            manager.beginTransaction().add(movieContainer,
                    MovieFragment.newInstance(null), MovieFragment.class.getSimpleName()).commit();
        }
    }

    private void setupSearchFragment(@IdRes int searchContainer, MovieFragmentSearchView fragmentSearch){
        if(fragmentSearch == null){
            manager.beginTransaction().add(searchContainer,
                    MovieFragmentSearchView.newInstance(null), MovieFragmentSearchView.class.getSimpleName()).commit();
        }
    }

    // TODO - on click in single pane (observe events from common model) - replace with detail fragment, addTransaction to back stack ...
}
