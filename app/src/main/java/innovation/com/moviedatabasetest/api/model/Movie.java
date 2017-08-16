package innovation.com.moviedatabasetest.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @JsonProperty("id") private long id;
    @JsonProperty("vote_average") private double voteScore;
    @JsonProperty("title") private String title;
    @JsonProperty("poster_path") private String posterPath;

    public static String getPosterBaseUrl() {
        return POSTER_BASE_URL;
    }

    public long getId() {
        return id;
    }

    public double getVoteScore() {
        return voteScore;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + (posterPath != null ? posterPath : "");
    }
}
