package innovation.com.moviedatabasetest.movie.fragmentpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;
import innovation.com.moviedatabasetest.di.module.MovieFragmentModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;
import innovation.com.moviedatabasetest.provider.db.Movie;


public class MovieFragment extends GenericFragment<IMovieFragment, IMovieFragmentPresenter>
        implements IMovieFragment {

    @Inject IMovieFragmentPresenter presenter;
    @Inject MoviePagerAdapter pagerAdapter;

    @BindView(R.id.fragment_tab_layout) TabLayout tabLayout;
    @BindView(R.id.fragment_view_pager) ViewPager viewPager;

    public MovieFragment() {
    }

    public static MovieFragment newInstance(@Nullable Bundle args) {
        final MovieFragment fragmentList = new MovieFragment();
        if (args != null) {
            fragmentList.setArguments(args);
        }
        return fragmentList;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MovieActivity) getActivity()).getComponent().addFragmentComponent(new MovieFragmentModule(this)).inject(this);
        bind(this, presenter, ButterKnife.bind(this, getView()));
        presenter.setupView(pagerAdapter, tabLayout, viewPager);
    }
}
