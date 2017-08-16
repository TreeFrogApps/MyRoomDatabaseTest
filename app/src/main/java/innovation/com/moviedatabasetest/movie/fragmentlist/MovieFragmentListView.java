package innovation.com.moviedatabasetest.movie.fragmentlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;
import innovation.com.moviedatabasetest.di.module.MovieFragmentListModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;


public class MovieFragmentListView extends GenericFragment<IMovieFragmentListView, IMovieFragmentListPresenter>
        implements IMovieFragmentListView {

    @Inject IMovieFragmentListPresenter presenter;

    public MovieFragmentListView() {}

    public static MovieFragmentListView newInstance(@Nullable Bundle args) {
        final MovieFragmentListView fragmentList = new MovieFragmentListView();
        if (args != null) {
            fragmentList.setArguments(args);
        }
        return fragmentList;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MovieActivity) getActivity()).getComponent().addFragmentListComponent(new MovieFragmentListModule()).inject(this);
        bind(this, presenter, ButterKnife.bind(getView()));
    }

    @Override public void movieList(List<String> movies) {

    }
}
