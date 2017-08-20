package innovation.com.moviedatabasetest.movie.fragmentpager;


import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import butterknife.Unbinder;

public class MovieFragmentPresenter implements IMovieFragmentPresenter {

    private final FragmentManager manager;

    private IMovieFragment view;
    private Unbinder unbinder;

    public MovieFragmentPresenter(FragmentManager manager) {
        this.manager = manager;
    }

    @Override public void bind(IMovieFragment iMovieFragment, Unbinder unbinder) {
        this.view = iMovieFragment;
        this.unbinder = unbinder;
    }

    @Override public void unbind(boolean isChangingConfigurations) {
        // any Disposable.dispose here...
    }

    @Override public void unbindView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override public void setupView(MoviePagerAdapter adapter, TabLayout tabLayout, ViewPager pager) {
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(adapter);
    }
}
