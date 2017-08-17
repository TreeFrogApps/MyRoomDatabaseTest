package innovation.com.moviedatabasetest.provider.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "movies_table", indices = {@Index(value = {"movie_id", "movie_type"}, unique = true)})
public class Movie {

    enum MovieType {
        INCINEMAS, POPULAR
    }

    static final String MOVIES_TABLE = "movies_table";
    static final String MOVIE_TYPE = "movie_type";
    static final String MOVIE_ID = "movie_id";
    static final String VOTE_AVERAGE = "vote_average";
    static final String TITLE = "title";
    static final String RELEASE_DATE = "release_date";
    static final String OVERVIEW = "overview";
    static final String POSTER_PATH = "poster_path";
    static final String IS_FAVOURITE = "is_favourite";

    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @PrimaryKey()
    String rowid;

    @JsonProperty("id")
    @ColumnInfo(name = MOVIE_ID)
    long movieId;

    @JsonProperty(VOTE_AVERAGE)
    @ColumnInfo(name = VOTE_AVERAGE)
    double voteScore;

    @JsonProperty(TITLE)
    @ColumnInfo(name = TITLE)
    String title;

    @JsonProperty(RELEASE_DATE)
    @ColumnInfo(name = RELEASE_DATE)
    String releaseDate;

    @JsonProperty(OVERVIEW)
    @ColumnInfo(name = OVERVIEW)
    String movieOverview;

    @JsonProperty(POSTER_PATH)
    @ColumnInfo(name = POSTER_PATH)
    String posterPath;

    @ColumnInfo(name = IS_FAVOURITE)
    boolean isFavourite;

    @ColumnInfo(name = MOVIE_TYPE)
    String movieType;

}
