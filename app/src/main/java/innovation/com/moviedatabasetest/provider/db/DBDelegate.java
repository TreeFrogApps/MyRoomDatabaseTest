package innovation.com.moviedatabasetest.provider.db;


import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.db.Movie.MovieType;
import io.reactivex.Flowable;
import io.reactivex.Single;

@ApplicationScope public final class DBDelegate {

    private final MovieDAO movieDAO;
    private final Executor dbExecutor;

    @Inject DBDelegate(MovieDatabase movieDatabase, Executor dbExecutor) {
        this.movieDAO = movieDatabase.movieDAO();
        this.dbExecutor = dbExecutor;
    }

    public Flowable<List<Movie>> getAll() {
        return movieDAO.getAll();
    }

    public Single<Movie> getByRowId(long rowId) {
        return movieDAO.getByRowId(rowId);
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
        dbExecutor.execute(() -> movieDAO.insert(movie));
    }

    public void insertMulti(List<Movie> movies) {
        movieDAO.insertMulti(movies);
    }

    public void updateMovies(List<Movie> movies) {
        dbExecutor.execute(() -> movieDAO.updateMovies(movies));
    }

    public void delete(Movie... movies) {
        dbExecutor.execute(() -> movieDAO.delete(movies));
    }

    public void deleteNonFavourites() {
        movieDAO.deleteNonFavourites();
    }

    public void deleteAll() {
        dbExecutor.execute(movieDAO::deleteAll);
    }
}
