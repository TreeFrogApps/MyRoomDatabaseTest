package innovation.com.moviedatabasetest.movie.fragmentsearch;

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
import innovation.com.moviedatabasetest.di.module.MovieFragmentSearchModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;


public class MovieFragmentSearchView extends GenericFragment<IMovieFragmentSearchView, IMovieFragmentSearchPresenter> implements IMovieFragmentSearchView {

    @Inject IMovieFragmentSearchPresenter presenter;

    public MovieFragmentSearchView() {
    }

    public static MovieFragmentSearchView newInstance(@Nullable Bundle args) {
        final MovieFragmentSearchView fragmentSearch = new MovieFragmentSearchView();
        if (args != null) {
            fragmentSearch.setArguments(args);
        }
        return fragmentSearch;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MovieActivity) getActivity()).getComponent().addFragmentSearchComponent(new MovieFragmentSearchModule()).inject(this);
        bind(this, presenter, ButterKnife.bind(getView()));
    }

    @Override public void searchResults(List<Object> results) {

    }
}
