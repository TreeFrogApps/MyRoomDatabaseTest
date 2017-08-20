package innovation.com.moviedatabasetest.common;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import innovation.com.moviedatabasetest.R;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static android.view.LayoutInflater.from;
import static butterknife.ButterKnife.bind;


public final class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> {

    private final List<Movie> movieList;
    private final PublishSubject<Movie> updateSubject;
    private final PublishSubject<Movie> openMovieSubject;

    public MovieRecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
        updateSubject = PublishSubject.create();
        openMovieSubject = PublishSubject.create();
    }

    public Observable<Movie> updateFavouriteObservable(){
        return updateSubject;
    }

    public Observable<Movie> movieClickedObservable(){
        return openMovieSubject;
    }


    @Override public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(from(parent.getContext()).inflate(R.layout.recycler_row, parent, false));
    }

    @Override public void onBindViewHolder(MovieHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.rowId.setText(String.valueOf(movie.movieId));
        holder.title.setText(movie.title);

        final PopupMenu menu = new PopupMenu(holder.itemView.getContext(), holder.menuButton);
        holder.menuButton.setOnClickListener(view -> openMenu(movie.isFavourite, movie, menu));
        holder.layout.setOnClickListener(view -> openMovieSubject.onNext(movie));
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

    private boolean updateItem(Movie movie, boolean change) {
        movie.isFavourite = change;
        updateSubject.onNext(movie);
        return true;
    }


    @Override public int getItemCount() {
        return movieList.size();
    }

    final static class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerLayout) LinearLayout layout;
        @BindView(R.id.recyclerRowId) TextView rowId;
        @BindView(R.id.recyclerRowMovieTitle) TextView title;
        @BindView(R.id.recyclerRowMenuButton) ImageView menuButton;

        MovieHolder(View itemView) {
            super(itemView);
            bind(this, itemView);
        }
    }
}
