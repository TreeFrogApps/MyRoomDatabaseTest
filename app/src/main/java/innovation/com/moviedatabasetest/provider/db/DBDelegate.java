package innovation.com.moviedatabasetest.provider.db;


import java.util.List;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.db.Movie.MovieType;
import io.reactivex.Flowable;
import io.reactivex.Single;

@ApplicationScope public final class DBDelegate {

    private final MovieDAO movieDAO;

    @Inject DBDelegate(MovieDatabase movieDatabase) {
        this.movieDAO = movieDatabase.movieDAO();
    }

    public Flowable<List<Movie>> getAll() {
        return movieDAO.getAll();
    }

    public Single<Movie> getByMovieId(long movieId) {
        return movieDAO.getByMovieId(movieId);
    }

    public Single<List<Movie>> searchByMovieName(String query) {
        return movieDAO.searchByMovieName(query);
    }

    public Flowable<List<Movie>> getByMovieType(MovieType type) {
        return movieDAO.getByMovieType(type.name());
    }

    public Flowable<List<Movie>> getFavourites() {
        return movieDAO.getFavourites(1);
    }

    public void insert(Movie movie) {
        movieDAO.insert(movie);
    }

    public void insertMulti(List<Movie> movies) {
        movieDAO.insertMulti(movies);
    }

    public void updateMovies(List<Movie> movies) {
        movieDAO.updateMovies(movies);
    }

    public void delete(Movie... movies) {
        movieDAO.delete(movies);
    }

    public void deleteNonFavourites() {
        movieDAO.deleteNonFavourites();
    }

    public void deleteAll() {
        movieDAO.deleteAll();
    }
}
