package innovation.com.moviedatabasetest.movie.fragmentdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;
import innovation.com.moviedatabasetest.di.module.MovieFragmentDetailModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;


public class MovieFragmentDetailView extends GenericFragment<IMovieFragmentDetailView, IMovieFragmentDetailPresenter> implements IMovieFragmentDetailView {

    private static final String TAG = MovieFragmentDetailView.class.getSimpleName();

    @Inject IMovieFragmentDetailPresenter presenter;

    public MovieFragmentDetailView() {
    }

    public static MovieFragmentDetailView newInstance(@Nullable Bundle args) {
        final MovieFragmentDetailView fragmentDetail = new MovieFragmentDetailView();
        if (args != null) {
            fragmentDetail.setArguments(args);
        }
        return fragmentDetail;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MovieActivity) getActivity()).getComponent().addFragmentDetailComponent(new MovieFragmentDetailModule()).inject(this);
        bind(this, presenter, ButterKnife.bind(getView()));
    }

    @Override public void updateMovieDetails(String title, String info, String imageUrl) {
        // TODO - update display
    }
}
