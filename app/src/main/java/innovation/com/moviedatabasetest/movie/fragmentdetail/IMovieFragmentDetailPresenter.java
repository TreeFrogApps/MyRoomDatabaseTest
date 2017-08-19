package innovation.com.moviedatabasetest.movie.fragmentdetail;


import innovation.com.moviedatabasetest.base.IPresenterLifeCycle;

public interface IMovieFragmentDetailPresenter extends IPresenterLifeCycle<IMovieFragmentDetailView> {

    void movieDetailRequest(long rowId);
}
