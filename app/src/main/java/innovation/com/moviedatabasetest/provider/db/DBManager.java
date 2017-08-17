package innovation.com.moviedatabasetest.provider.db;


import java.util.List;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.db.Movie.MovieType;
import io.reactivex.Flowable;
import io.reactivex.Single;

@ApplicationScope public final class DBManager {

    private final MovieDAO movieDAO;

    @Inject DBManager(MovieDatabase movieDatabase) {
        this.movieDAO = movieDatabase.movieDAO();
    }

    Flowable<List<Movie>> getAll() {
        return movieDAO.getAll();
    }

    Single<List<Movie>> getByMovieIds(int[] movieIds) {
        return  movieDAO.getByMovieIds(movieIds);
    }

    Single<List<Movie>> searchByMovieName(String query) {
        return movieDAO.searchByMovieName(query);
    }

    Flowable<List<Movie>> getByMovieType(MovieType type) {
        return movieDAO.getByMovieType(type.name());
    }

    Flowable<List<Movie>> getFavourites() {
        return movieDAO.getFavourites(1);
    }

    void insert(Movie movie) {

    }

    void insertMulti(Movie... movies) {

    }

    void updateMovies(Movie... movies) {

    }

    void delete(Movie... movies) {

    }

    void deleteAll() {

    }
}
