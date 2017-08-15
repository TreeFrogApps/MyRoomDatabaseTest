package innovation.com.moviedatabasetest.movie;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.MVP.IMovieModel;
import innovation.com.moviedatabasetest.movie.fragmentdetail.MovieFragmentDetail;
import innovation.com.moviedatabasetest.movie.fragmentlist.MovieFragmentList;
import innovation.com.moviedatabasetest.movie.fragmentsearch.MovieFragmentSearch;

import static innovation.com.moviedatabasetest.movie.MVP.*;


public class MoviePresenter extends GenericPresenter<IMovieModel> implements IMoviePresenter {

    private final Context context;
    private final IMovieModel model;
    private final FragmentManager manager;

    @Nullable private Unbinder unbinder;
    private boolean dualPane;

    public MoviePresenter(Context context, IMovieModel model, FragmentManager manager) {
        this.context = context;
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieView iMovieView, Unbinder unbinder) {
        this.unbinder = unbinder;
        model.bind();
    }

    @Override public void bind(IMovieView iMovieView) {

    }

    @Override public void unbind(boolean isChangingConfigurations) {
        model.unbind(isChangingConfigurations);
    }

    @Override public void unbindView() {
        if (unbinder != null) unbinder.unbind();
    }


    @Override public void setupView(@IdRes int movieContainer, @IdRes int searchContainer, @IdRes int detailContainer, boolean dualPane) {
        final MovieFragmentSearch fragmentSearch = (MovieFragmentSearch) manager.findFragmentByTag(MovieFragmentSearch.class.getSimpleName());
        final MovieFragmentDetail fragmentDetail = (MovieFragmentDetail) manager.findFragmentByTag(MovieFragmentDetail.class.getSimpleName());
        final MovieFragmentList fragmentList = (MovieFragmentList) manager.findFragmentByTag(MovieFragmentList.class.getSimpleName());

        setupSearchFragment(searchContainer, fragmentSearch);

        if(dualPane){
            setupDetailFragment(detailContainer, fragmentDetail);
            setupListFragment(movieContainer, fragmentList);
        } else {
            setupListFragment(movieContainer, fragmentList);
        }
    }

    private void setupDetailFragment(@IdRes int detailContainer, MovieFragmentDetail fragmentDetail) {
        if(fragmentDetail == null){
            manager.beginTransaction().add(detailContainer,
                    MovieFragmentDetail.newInstance(null), MovieFragmentDetail.class.getSimpleName()).commit();
        }
    }

    private void setupListFragment(@IdRes int movieContainer, MovieFragmentList fragmentList) {
        if(fragmentList == null){
            manager.beginTransaction().add(movieContainer,
                    MovieFragmentList.newInstance(null), MovieFragmentList.class.getSimpleName()).commit();
        }
    }

    private void setupSearchFragment(@IdRes int searchContainer, MovieFragmentSearch fragmentSearch){
        if(fragmentSearch == null){
            manager.beginTransaction().add(searchContainer,
                    MovieFragmentSearch.newInstance(null), MovieFragmentSearch.class.getSimpleName()).commit();
        }
    }

    // TODO - on click in single pane (observe events from common model) - replace with detail fragment, addTransaction to back stack ...
}
