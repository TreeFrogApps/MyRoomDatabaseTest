package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static android.view.LayoutInflater.from;
import static butterknife.ButterKnife.bind;


public final class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> {

    private final List<Movie> movieList;

    public MovieRecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }


    @Override public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(from(parent.getContext()).inflate(R.layout.recycler_row, parent, false));
    }

    @Override public void onBindViewHolder(MovieHolder holder, int position) {
        final Movie movie = movieList.get(position);

    }

    @Override public int getItemCount() {
        return movieList.size();
    }


    final class MovieHolder extends RecyclerView.ViewHolder {
        private PopupMenu menu;
        private final  PublishSubject<Movie> movieSubject;
        @BindView(R.id.recyclerRowId) TextView rowId;
        @BindView(R.id.recyclerRowMovieTitle) TextView title;
        @BindView(R.id.recyclerRowMenuButton) ImageView menuButton;

        MovieHolder(View itemView) {
            super(itemView);
            bind(this, itemView);
            movieSubject = PublishSubject.create();
            menu = new PopupMenu(itemView.getContext(), menuButton);
        }

        @OnClick(R.id.recyclerRowMenuButton)
        public void setMenuButtonClick(){
            final Movie movie = movieList.get(getAdapterPosition());
            openMenu(movie.isFavourite, movie, menu);
        }

        private void openMenu(boolean isFavourite, Movie movie, PopupMenu menu) {
            menu.getMenu().clear();
            if(isFavourite) {
                menu.getMenuInflater().inflate(R.menu.pop_menu_remove, menu.getMenu());
            } else {
                menu.getMenuInflater().inflate(R.menu.pop_menu_add, menu.getMenu());
            }
            menu.setOnMenuItemClickListener(item -> updateItem(movie, !isFavourite));
            menu.show();
        }

        public boolean updateItem(Movie movie, boolean change) {
            movie.isFavourite = change;
            movieSubject.onNext(movie);
            return true;
        }

        public Observable<Movie> observeChanges() {
            return movieSubject;
        }
    }
}
