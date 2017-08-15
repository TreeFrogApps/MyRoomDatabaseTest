package innovation.com.moviedatabasetest.movie.fragmentlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;


public class MovieFragmentList extends GenericFragment {

    public MovieFragmentList() {}

    public static MovieFragmentList newInstance(@Nullable Bundle args) {
        final MovieFragmentList fragmentList = new MovieFragmentList();
        if (args != null) {
            fragmentList.setArguments(args);
        }
        return fragmentList;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
