package io.sureshg.flicks.mdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The Movie Database API interface.
 *
 * @see http://docs.themoviedb.apiary.io/#reference/movies/movielatest
 */
public interface MovieDB {

    /**
     * API end point base url.
     */
    String BASE_URL = "https://api.themoviedb.org/3/";

    /**
     * Movies DB shared api key.
     */
    String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";


    /**
     * Get the list of movies playing that have been, or are being released this week. This list refreshes every day.
     *
     * @param apiKey API key
     * @param page   Minimum 1, maximum 1000.
     * @return ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Call<MovieResult> nowPlaying(@Query("api_key") String apiKey, @Query("page") int page);
}
