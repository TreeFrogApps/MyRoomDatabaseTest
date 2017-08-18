package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


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
import innovation.com.moviedatabasetest.di.module.MovieFragmentModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;
import innovation.com.moviedatabasetest.provider.db.Movie;

public class MovieItemsFragment extends GenericFragment<IMovieFragmentItems, IMovieFragmentItemsPresenter>
        implements IMovieFragmentItems {

    @Inject IMovieFragmentItemsPresenter presenter;
    @BindView(R.id.moveListRecyclerView) RecyclerView recyclerView;

    private MovieRecyclerAdapter recyclerAdapter;
    private List<Movie> movieList;
    private int id;

    public static final String LIST_ID_KEY = "list_items_id";

    public static MovieItemsFragment newInstance(@Nullable Bundle args) {
        MovieItemsFragment fragment = new MovieItemsFragment();
        if (args != null && args.getInt(LIST_ID_KEY, -1) != -1) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MovieActivity) getActivity()).getComponent().addFragmentComponent(new MovieFragmentModule(this)).inject(this);
        movieList = new ArrayList<>();
        recyclerAdapter = new MovieRecyclerAdapter(movieList);
        presenter.bind(this, ButterKnife.bind(this, getView()));
        if (getArguments() != null) {
            id = getArguments().getInt(LIST_ID_KEY);
        }
        presenter.setupView(getActivity(), recyclerView, recyclerAdapter, id);
    }

    // TODO - hook into api and database - presenter.bind
    // TODO - complete rxchain / chains in MovieProvider
    // TODO - implement search fragment .... connect to api - insert/update adding as favourite to favourites?
    @Override public void movieList(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        recyclerAdapter.notifyDataSetChanged();
    }
}
