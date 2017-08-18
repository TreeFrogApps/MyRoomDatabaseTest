package innovation.com.moviedatabasetest.movie.fragmentpager.fragmentitems;


import java.util.List;

import innovation.com.moviedatabasetest.provider.db.Movie;
import innovation.com.moviedatabasetest.provider.db.Movie.MovieType;

public interface IMovieFragmentItems {

    void movieList(List<Movie> movies);
}
