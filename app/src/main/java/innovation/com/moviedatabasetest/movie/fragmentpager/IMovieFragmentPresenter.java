package innovation.com.moviedatabasetest.movie.fragmentpager;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.List;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;
import io.reactivex.Observable;

public interface IMovieFragmentPresenter extends IPresenterLifeCycle<IMovieFragment> {

    void setupView(MoviePagerAdapter adapter, TabLayout tabLayout, ViewPager pager);
}
