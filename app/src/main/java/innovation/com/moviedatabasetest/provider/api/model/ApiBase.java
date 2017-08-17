package innovation.com.moviedatabasetest.provider.api.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import innovation.com.moviedatabasetest.provider.db.Movie;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class ApiBase {

    @JsonProperty("page") private int page;
    @JsonProperty("total_results") private int totalResults;
    @JsonProperty("total_pages") private int totalPages;
    @JsonProperty("results") private List<Movie> movieList = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public boolean hasNextPage(){
        return page < totalPages;
    }
}
