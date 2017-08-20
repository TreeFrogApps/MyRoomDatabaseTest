package innovation.com.moviedatabasetest.movie;


import java.util.List;

import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMovieSharedModel {

    Single<Movie> getMovie(long rowId);

    Flowable<List<Movie>> getMovieList(int id);

    void updateMovies();

    void performMovieSearch(String query);

    Observable<List<Movie>> subscribeToSearchResults();

    void updateMovie(Movie movie);

    void insertMovie(Movie movie);

    Observable<Movie> subscribeDetailMovie();

    void updateDetailMovie(Movie movie);

    void setDualPane(boolean isDualPane);

    boolean isDualPane();

    boolean isSearching();

    void setSearching(boolean isSearching);
}
