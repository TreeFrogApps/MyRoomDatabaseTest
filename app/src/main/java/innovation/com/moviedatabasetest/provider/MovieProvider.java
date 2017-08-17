package innovation.com.moviedatabasetest.provider;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.common.Date;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.api.ApiManager;
import innovation.com.moviedatabasetest.provider.db.DBManager;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static java.util.concurrent.TimeUnit.DAYS;

@ApplicationScope public final class MovieProvider {

    private static final long ONE_WEEK = DAYS.toMillis(7);

    private final DBManager DBmanager;
    private final ApiManager apiManager;
    private final Date date;

    @Inject public MovieProvider(DBManager DBmanager, ApiManager apiManager, Date date) {
        this.DBmanager = DBmanager;
        this.apiManager = apiManager;
        this.date = date;
    }

    public Single<Movie> getMovieInfo(String movie) {
        return null;
    }

    public Flowable<List<Movie>> getMovieList(long lastUpdate) {
        if((lastUpdate + ONE_WEEK) > date.now()){
            // TODO - get all objects from Movie table
        } else {
            // TODO - remove all from table and request again from api
        }
        return null;
    }

    public Single<List<Movie>> performMovieSearch(String query) {
        return null;
    }
}
