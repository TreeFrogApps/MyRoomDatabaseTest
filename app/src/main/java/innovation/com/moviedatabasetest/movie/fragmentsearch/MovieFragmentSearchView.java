package innovation.com.moviedatabasetest.movie.fragmentsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;
import innovation.com.moviedatabasetest.common.MovieRecyclerAdapter;
import innovation.com.moviedatabasetest.di.module.MovieFragmentSearchModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;
import innovation.com.moviedatabasetest.provider.db.Movie;


public class MovieFragmentSearchView extends GenericFragment<IMovieFragmentSearchView, IMovieFragmentSearchPresenter> implements IMovieFragmentSearchView {

    @BindView(R.id.searchRecyclerView) RecyclerView resultsRecyclerView;
    @Inject IMovieFragmentSearchPresenter presenter;

    private List<Movie> resultsList;
    private MovieRecyclerAdapter movieRecyclerAdapter;

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
        resultsList = new ArrayList<>();
        movieRecyclerAdapter = new MovieRecyclerAdapter(resultsList);
        bind(this, presenter, ButterKnife.bind(this, getView()));
        presenter.setupView(resultsRecyclerView, movieRecyclerAdapter, getActivity());
        presenter.subscribeToSearchEvents();
    }

    @Override public void searchResults(List<Movie> results, String searchText) {
        resultsList.clear();
        resultsList.addAll(results);
        final int color = 0xFF428B38;
        movieRecyclerAdapter.setSubText(searchText, color);
        movieRecyclerAdapter.notifyDataSetChanged();
    }
}
