package innovation.com.moviedatabasetest.movie.fragmentsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericFragment;


public class MovieFragmentSearch extends GenericFragment {

    public MovieFragmentSearch() {}

    public static MovieFragmentSearch newInstance(@Nullable Bundle args){
        final MovieFragmentSearch fragmentSearch = new MovieFragmentSearch();
        if(args != null){
            fragmentSearch.setArguments(args);
        }
        return fragmentSearch;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
