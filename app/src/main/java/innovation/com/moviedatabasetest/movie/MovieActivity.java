package innovation.com.moviedatabasetest.movie;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.base.GenericActivity;
import innovation.com.moviedatabasetest.di.component.MovieActivityComponent;
import innovation.com.moviedatabasetest.di.module.MovieActivityModule;

import static innovation.com.moviedatabasetest.MovieApp.getRoomAppComponent;
import static innovation.com.moviedatabasetest.movie.MVP.*;

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
                R.id.movieFragmentContainer,
                R.id.searchFragmentContainer,
                R.id.movieDetailContainer,
                findViewById(R.id.movieDetailContainer) != null);
    }

    @Override protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_search){
            // TODO - sort search
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



    @Override protected void onDestroy() {
        super.onDestroy();
        unbind(isChangingConfigurations());
    }

    @Override protected MovieActivityComponent getComponent(){
        return component;
    }

}
