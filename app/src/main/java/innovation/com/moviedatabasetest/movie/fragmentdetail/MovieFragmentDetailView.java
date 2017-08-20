package innovation.com.moviedatabasetest.movie.fragmentdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;
import innovation.com.moviedatabasetest.di.module.MovieFragmentDetailModule;
import innovation.com.moviedatabasetest.movie.MovieActivity;
import innovation.com.moviedatabasetest.provider.db.Movie;


public class MovieFragmentDetailView extends GenericFragment<IMovieFragmentDetailView, IMovieFragmentDetailPresenter> implements IMovieFragmentDetailView {

    public static final String DETAIL_FRAGMENT_ROW_ID_KEY = "detail_fragment_row_id";
    private long rowId;

    @Inject IMovieFragmentDetailPresenter presenter;
    @BindView(R.id.movieDetailTitle) TextView title;
    @BindView(R.id.movieDetailSubtitle) TextView subtitle;
    @BindView(R.id.movieDetailMainText) TextView movieDetails;
    @BindView(R.id.movieDetailReleaseDate) TextView releaseDate;
    @BindView(R.id.movieDetailImage) ImageView movieImage;

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
        if (getArguments() != null) rowId = getArguments().getLong(DETAIL_FRAGMENT_ROW_ID_KEY, -1);
        bind(this, presenter, ButterKnife.bind(this, getView()));
        presenter.subscribeToUpdates();
    }

    @Override public void updateMovieDetails(Movie movie) {
        title.setText(movie.title);
        subtitle.setText(String.valueOf(movie.voteScore));
        movieDetails.setText(movie.movieOverview);
        releaseDate.setText(movie.releaseDate);
        Glide.with(getActivity())
                .load(movie.getAppendedPosterPath())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(movieImage);
    }
}
