package innovation.com.moviedatabasetest.movie;


import android.support.annotation.IdRes;

import innovation.com.moviedatabasetest.base.IModelLifeCycle;
import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;

public interface MVP {

    interface IMovieView {

    }

    interface IMoviePresenter extends IPresenterLifeCycle<IMovieView> {

        void setupView(@IdRes int movieContainer, @IdRes int searchContainer, @IdRes int detailContainer, boolean dualPane);
    }

    interface IMovieModel extends IModelLifeCycle {

    }
}
