package innovation.com.moviedatabasetest.movie.fragmentlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import innovation.com.moviedatabasetest.R;


public final class MoviePagerAdapter extends FragmentStatePagerAdapter {

    private final String[] titles;

    public MoviePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        titles = context.getResources().getStringArray(R.array.item_titles);
    }

    @Override public Fragment getItem(int position) {
        final Bundle bundle = new Bundle(1);
        bundle.putInt(MovieItemsFragment.LIST_ID_KEY, position);
        return MovieItemsFragment.newInstance(bundle);
    }

    @Override public int getCount() {
        return titles.length;
    }

    @Override public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
