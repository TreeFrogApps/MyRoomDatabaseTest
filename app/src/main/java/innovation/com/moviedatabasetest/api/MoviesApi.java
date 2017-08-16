package innovation.com.moviedatabasetest.api;


import innovation.com.moviedatabasetest.api.model.InCinemas;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("discover/movie?region=GB")
    @Headers("Accept: application/json")
    Observable<InCinemas> getInCinemasList(@Query("primary_release_date.gte") String fromDate,
                                           @Query("primary_release_date.lte") String toDate, @Query("page") String page);
}
