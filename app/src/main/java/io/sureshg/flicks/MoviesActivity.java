package io.sureshg.flicks;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.sureshg.flicks.mdb.Movie;
import io.sureshg.flicks.mdb.MovieDB;
import io.sureshg.flicks.mdb.MovieResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.sureshg.flicks.FlicksApp.TAG;
import static io.sureshg.flicks.FlicksApp.moviesDB;

/**
 * Movies launcher activity.
 *
 * @author Suresh
 */
public class MoviesActivity extends AppCompatActivity {

    @BindView(R.id.lvMovies)
    ListView lvMovies;

    List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        setLogo();

        final MoviesArrayAdapter moviesAdapter = new MoviesArrayAdapter(this, movies);
        lvMovies.setAdapter(moviesAdapter);

        moviesDB.nowPlaying(MovieDB.API_KEY, 1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    movies.addAll(response.body().results);
                    moviesAdapter.notifyDataSetChanged();
                } else {
                    try {
                        Log.e(TAG, "Invalid movie list response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e(TAG, "Failed to get the movie list.", t);
                Toast.makeText(MoviesActivity.this, "Movie fetching failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Sets application logo in action bar.
     */
    private void setLogo() {
        ActionBar aBar = getSupportActionBar();
        if (aBar != null) {
            aBar.setDisplayShowHomeEnabled(true);
            aBar.setLogo(R.mipmap.ic_launcher);
            aBar.setTitle(R.string.app_title);
            aBar.setDisplayUseLogoEnabled(true);
        }
    }
}
