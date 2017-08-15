package innovation.com.moviedatabasetest.movie.fragmentdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;


public class MovieFragmentDetail extends GenericFragment {

    public MovieFragmentDetail() {}

    public static MovieFragmentDetail newInstance(@Nullable Bundle args){
        final MovieFragmentDetail fragmentDetail = new MovieFragmentDetail();
        if(args != null) {
            fragmentDetail.setArguments(args);
        }
        return fragmentDetail;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
