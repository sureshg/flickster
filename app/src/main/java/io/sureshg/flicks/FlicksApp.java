package io.sureshg.flicks;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import io.sureshg.flicks.mdb.Movie;
import io.sureshg.flicks.mdb.MovieDB;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static io.sureshg.flicks.FlicksApp.TAG;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Flicks application instance
 *
 * @author Suresh
 */
public class FlicksApp extends Application {

    /**
     * Flicks application tag for logging.
     */
    public static final String TAG = "Flicks";

    /**
     * Movies DB client.
     */
    public static MovieDB moviesDB;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Initializing Flicks app and Stetho...");
        Stetho.initializeWithDefaults(this);
        moviesDB = initMoviesDBClient();
    }

    /**
     * Initializes movies db http client.
     *
     * @return {@link MovieDB}
     */
    private MovieDB initMoviesDBClient() {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(5, SECONDS)
                .readTimeout(5, SECONDS)
                .writeTimeout(5, SECONDS)
                .build();

        Moshi moshi = new Moshi.Builder().add(new Movie.DateAdapter()).build();

        return new Retrofit.Builder()
                .baseUrl(MovieDB.BASE_URL)
                .client(okHttp)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(MovieDB.class);
    }
}

/**
 * Http logging interceptor.
 */
class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        long t1 = System.nanoTime();
        Log.d(TAG, String.format("Sending request %s on %s%n%s", req.url(), chain.connection(), req.headers()));

        Response res = chain.proceed(req);

        long t2 = System.nanoTime();
        Log.d(TAG, String.format("Received response for %s in %.1fms%n%s", res.request().url(), (t2 - t1) / 1e6d, res.headers()));
        return res;
    }
}

