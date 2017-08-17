package innovation.com.moviedatabasetest.provider.api;


import innovation.com.moviedatabasetest.provider.api.model.ApiBase;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("discover/movie?region=GB")
    @Headers("Accept: application/json")
    Observable<ApiBase> getInCinemasList(@Query("primary_release_date.gte") String fromDate,
                                         @Query("primary_release_date.lte") String toDate, @Query("page") String page);

    @GET("discover/movie?sort_by=popularity.desc&region=GB")
    @Headers("Accept: application/json")
    Observable<ApiBase> getPopularList(@Query("page") String page);
}
