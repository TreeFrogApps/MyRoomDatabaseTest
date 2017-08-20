package innovation.com.moviedatabasetest.movie;


import android.support.annotation.IdRes;
import android.support.v7.widget.SearchView;
import android.widget.FrameLayout;

import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;

public interface IMoviePresenter extends IPresenterLifeCycle<IMovieView> {

    void setupView(FrameLayout searchLayout, @IdRes int movieContainer, @IdRes int searchContainer, @IdRes int detailContainer, boolean dualPane);

    void openSearchFragment(FrameLayout frameLayout);

    boolean closeSearchFragment(FrameLayout frameLayout, SearchView searchView);

    void performSearchQuery(String query);
}
