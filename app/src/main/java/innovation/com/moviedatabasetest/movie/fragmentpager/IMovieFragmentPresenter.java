package innovation.com.moviedatabasetest.movie.fragmentpager;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;

public interface IMovieFragmentPresenter extends IPresenterLifeCycle<IMovieFragment> {

    void setupView(MoviePagerAdapter adapter, TabLayout tabLayout, ViewPager pager);
}
