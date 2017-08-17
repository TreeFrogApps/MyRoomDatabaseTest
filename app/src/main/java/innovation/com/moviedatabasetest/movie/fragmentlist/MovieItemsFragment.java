package innovation.com.moviedatabasetest.movie.fragmentlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import innovation.com.moviedatabasetest.R;

public class MovieItemsFragment extends Fragment {

    static final String LIST_ID_KEY = "list_items_id";

    public static MovieItemsFragment newInstance(@Nullable Bundle args) {
        MovieItemsFragment fragment = new MovieItemsFragment();
        if(args != null && args.getInt(LIST_ID_KEY, -1) != -1){
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
