package innovation.com.moviedatabasetest.movie.fragmentsearch;


import java.util.List;

import innovation.com.moviedatabasetest.provider.db.Movie;

public interface IMovieFragmentSearchView {

    void searchResults(List<Movie> results, String searchText);
}
