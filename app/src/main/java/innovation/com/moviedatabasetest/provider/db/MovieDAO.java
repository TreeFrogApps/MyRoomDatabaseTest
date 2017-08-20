package innovation.com.moviedatabasetest.provider.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static innovation.com.moviedatabasetest.provider.db.Movie.IS_FAVOURITE;
import static innovation.com.moviedatabasetest.provider.db.Movie.MOVIES_TABLE;
import static innovation.com.moviedatabasetest.provider.db.Movie.MOVIE_TYPE;
import static innovation.com.moviedatabasetest.provider.db.Movie.ROW_ID;
import static innovation.com.moviedatabasetest.provider.db.Movie.TITLE;


@Dao interface MovieDAO {

    @Query("SELECT * FROM " + MOVIES_TABLE)
    Flowable<List<Movie>> getAll();

    @Query("SELECT * FROM " + MOVIES_TABLE + " " +
            "WHERE " + ROW_ID + "=" +
            ":rowId")
    Single<Movie> getByRowId(long rowId);

    @Query("SELECT * FROM " + MOVIES_TABLE + " " +
            "WHERE " + TITLE + " " +
            "LIKE :query " +
            "COLLATE NOCASE")
    Single<List<Movie>> searchByMovieName(String query);

    @Query("SELECT * FROM " + MOVIES_TABLE + " " +
            "WHERE " + MOVIE_TYPE + "=" +
            ":type")
    Flowable<List<Movie>> getByMovieType(String type);

    @Query("SELECT * FROM " + MOVIES_TABLE + " " +
            "WHERE " + IS_FAVOURITE + "=" +
            ":isFavourite")
    Flowable<List<Movie>> getFavourites(int isFavourite);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMulti(List<Movie> movies);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovies(List<Movie> movies);

    @Delete void delete(Movie... movies);

    @Query("DELETE FROM " + MOVIES_TABLE)
    void deleteAll();

    @Query("DELETE FROM " + MOVIES_TABLE + " " +
            "WHERE " + IS_FAVOURITE + "=0")
    void deleteNonFavourites();
}
