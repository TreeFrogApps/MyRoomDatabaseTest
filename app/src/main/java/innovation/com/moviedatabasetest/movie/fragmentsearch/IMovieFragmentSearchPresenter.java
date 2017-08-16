package innovation.com.moviedatabasetest.movie.fragmentsearch;


import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;

public interface IMovieFragmentSearchPresenter extends IPresenterLifeCycle<IMovieFragmentSearchView> {

    void movieSearch(String searchQuery);
}
