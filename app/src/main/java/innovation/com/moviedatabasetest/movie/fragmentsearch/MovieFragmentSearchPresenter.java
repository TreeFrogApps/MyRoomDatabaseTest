package innovation.com.moviedatabasetest.movie.fragmentsearch;


import android.support.v4.app.FragmentManager;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;

public class MovieFragmentSearchPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentSearchPresenter {

    private final IMovieSharedModel model;
    private final FragmentManager manager;

    private IMovieFragmentSearchView view;
    private Unbinder unbinder;

    public MovieFragmentSearchPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentSearchView iMovieFragmentSearchView, Unbinder unbinder) {
        this.view = iMovieFragmentSearchView;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {

    }

    @Override public void unbindView() {
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    @Override public void movieSearch(String searchQuery) {

    }
}
