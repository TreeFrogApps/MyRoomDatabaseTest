package innovation.com.moviedatabasetest.movie;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericActivity;
import innovation.com.moviedatabasetest.di.component.MovieActivityComponent;
import innovation.com.moviedatabasetest.di.module.MovieActivityModule;

import static innovation.com.moviedatabasetest.MovieApp.getRoomAppComponent;

public class MovieActivity extends GenericActivity<IMovieView, IMoviePresenter, MovieActivityComponent> implements IMovieView {

    private MovieActivityComponent component;
    @Inject IMoviePresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_layout));
        component = getRoomAppComponent(this).addRoomActivityComponent(new MovieActivityModule(this));
        component.inject(this);
        bind(this, presenter);

        presenter.setupView(
                (FrameLayout) findViewById(R.id.searchFragmentContainer),
                R.id.movieFragmentContainer,
                R.id.searchFragmentContainer,
                R.id.movieDetailContainer,
                findViewById(R.id.movieDetailContainer) != null);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        final FrameLayout searchLayout = (FrameLayout) findViewById(R.id.searchFragmentContainer);
        final SearchView searchView = ((SearchView) menu.findItem(R.id.menuSearch).getActionView());
        searchView.setOnSearchClickListener(v -> presenter.openSearchFragment(searchLayout));
        searchView.setOnCloseListener(() -> presenter.closeSearchFragment(searchLayout, searchView));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                presenter.performSearchQuery(query);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                presenter.performSearchQuery(newText);
                Log.e("SEARCH", "TEXT : " + newText);
                return true;
            }
        });
        return true;
    }

    @Override protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return true;
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        unbind(isChangingConfigurations());
    }

    @Override public MovieActivityComponent getComponent() {
        return component;
    }

}
