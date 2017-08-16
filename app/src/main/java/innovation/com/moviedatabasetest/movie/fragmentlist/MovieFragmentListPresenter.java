package innovation.com.moviedatabasetest.movie.fragmentlist;


import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;
import innovation.com.moviedatabasetest.base.GenericPresenter;
import innovation.com.moviedatabasetest.movie.IMovieSharedModel;
import io.reactivex.Observable;

public class MovieFragmentListPresenter extends GenericPresenter<IMovieSharedModel>
        implements IMovieFragmentListPresenter {

    private final IMovieSharedModel model;
    private final FragmentManager manager;

    private IMovieFragmentListView view;
    private Unbinder unbinder;

    public MovieFragmentListPresenter(IMovieSharedModel model, FragmentManager manager) {
        this.model = model;
        this.manager = manager;
    }

    @Override public void bind(IMovieFragmentListView iMovieFragmentListView, Unbinder unbinder) {
        this.view = iMovieFragmentListView;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {
        // any Disposable.dispose here...
    }

    @Override public void unbindView() {
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    @Override public Observable<List<String>> getMovieList() {
        //TODO - get list from DB
        view.movieList(new ArrayList<>());
        return null;
    }
}
