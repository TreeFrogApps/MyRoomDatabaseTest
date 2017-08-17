package innovation.com.moviedatabasetest.provider.api;


import java.util.List;

import javax.inject.Inject;

import innovation.com.moviedatabasetest.provider.api.model.ApiBase;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import io.reactivex.Observable;
import io.reactivex.Single;

import static java.util.concurrent.TimeUnit.DAYS;

@ApplicationScope public final class ApiManager {

    private static final long FOUR_WEEKS = DAYS.toMillis(28);
    private static final int MAX_PAGE = 4;
    private final MoviesApi moviesApi;
    private final DateProvider dateProvider;

    @Inject public ApiManager(MoviesApi moviesApi, DateProvider dateProvider) {
        this.moviesApi = moviesApi;
        this.dateProvider = dateProvider;
    }

    public Single<List<Object>> getInCinemasList() {
        final long now = System.currentTimeMillis();
        final String fromDate = dateProvider.formatDate(now, FOUR_WEEKS);
        final String toDate = dateProvider.formatDate(now, 0);
        return null;
//        return Observable.defer(() -> getInCinemas(fromDate, toDate, 1)
//                .subscribeOn(Schedulers.io())
//                .map()
//                .doOnNext() //TODO - save to DB using Room - read docs NOW!!
//                .collect());
    }

    private Observable<ApiBase> getInCinemas(String fromDate, String toDate, int page) {
        return moviesApi.getInCinemasList(fromDate, toDate, String.valueOf(page))
                .concatMap(apiBase -> apiBase.hasNextPage() && page <= MAX_PAGE ?
                        Observable.just(apiBase).concatWith(getInCinemas(fromDate, toDate, page + 1)) :
                        Observable.just(apiBase));
    }

    private Object convertToDbObject(ApiBase apiBase){
        return null;
        // TODO - convert to DB object - needs Room annontation .. setup gradle deps -a
    }
}
