package innovation.com.moviedatabasetest.di.module;


import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import dagger.Module;
import dagger.Provides;
import innovation.com.moviedatabasetest.provider.api.DateProvider;
import innovation.com.moviedatabasetest.provider.api.MoviesApi;
import innovation.com.moviedatabasetest.di.scope.ApplicationScope;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.util.Base64.NO_PADDING;
import static android.util.Base64.decode;
import static java.util.concurrent.TimeUnit.SECONDS;

@Module public class MovieNetModule {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "api_key";
    private static final String API_KEY_64 = "Yzg1MDc2MGU3MTY1MjA1ZDI3ODRlZjhlNTRkMTE1MGY=";

    @Provides @ApplicationScope Cache provideCache(Context context) {
        final int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides @ApplicationScope Interceptor provideApiKeyInterceptor() {
        return chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter(API_KEY, new String(decode(API_KEY_64, NO_PADDING))).build();
            return chain.proceed(chain.request().newBuilder().url(url).build());
        };
    }

    @Provides @ApplicationScope OkHttpClient provideClient(Cache cache, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10L, SECONDS)
                .readTimeout(10L, SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides @ApplicationScope Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client).build();
    }

    @Provides @ApplicationScope MoviesApi provideMoviesApi(Retrofit retrofit){
        return retrofit.create(MoviesApi.class);
    }

    @SuppressLint("SimpleDateFormat") @Provides @ApplicationScope DateProvider provideDate(){
        return (now, offset) -> new SimpleDateFormat("yyyy-MM-dd").format(new Date((now - offset)));
    }
}
