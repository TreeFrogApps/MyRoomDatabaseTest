package innovation.com.moviedatabasetest.provider.api;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import innovation.com.moviedatabasetest.provider.api.model.ApiBase;
import innovation.com.moviedatabasetest.provider.db.Movie;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import static java.util.concurrent.TimeUnit.DAYS;

@ApplicationScope public final class ApiManager {

    private static final long FOUR_WEEKS = DAYS.toMillis(28);
    private static final int MAX_PAGE = 6;
    private final MoviesApi moviesApi;
    private final DateProvider dateProvider;

    @Inject ApiManager(MoviesApi moviesApi, DateProvider dateProvider) {
        this.moviesApi = moviesApi;
        this.dateProvider = dateProvider;
    }

    public Flowable<List<Movie>> getInCinemasList() {
        final long now = System.currentTimeMillis();
        final String fromDate = dateProvider.formatDate(now, FOUR_WEEKS);
        final String toDate = dateProvider.formatDate(now, 0);
        return Flowable.defer(() -> getInCinemas(fromDate, toDate, 1)
                .subscribeOn(Schedulers.io())
                .collect(ArrayList<Movie>::new, (movies, apiBase) -> movies.addAll(apiBase.getMovieList()))
                .toFlowable());
    }

    private Flowable<ApiBase> getInCinemas(String fromDate, String toDate, int page) {
        return moviesApi.getInCinemasList(fromDate, toDate, String.valueOf(page))
                .concatMap(apiBase -> apiBase.hasNextPage() && page <= MAX_PAGE ?
                        Flowable.just(apiBase).concatWith(getInCinemas(fromDate, toDate, page + 1)) :
                        Flowable.just(apiBase));
    }

    public Flowable<List<Movie>> getPopularList() {
        return Flowable.defer(() -> getPopular(1)
                .subscribeOn(Schedulers.io())
                .collect(ArrayList<Movie>::new, (movies, apiBase) -> movies.addAll(apiBase.getMovieList()))
                .toFlowable());
    }

    private Flowable<ApiBase> getPopular(int page) {
        return moviesApi.getPopularList(String.valueOf(page))
                .concatMap(apiBase -> apiBase.hasNextPage() && page <= MAX_PAGE ?
                        Flowable.just(apiBase).concatWith(getPopular(page + 1)) :
                        Flowable.just(apiBase));
    }

}
