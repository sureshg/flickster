package io.sureshg.flicks.mdb;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Movie model.
 */
public final class Movie {

    @Json(name = "poster_path")
    public final String posterPath;

    public final boolean adult;

    public final String overview;

    @Json(name = "release_date")
    public final Date releaseDate;

    @Json(name = "genre_ids")
    public final int[] genreIds;

    public final long id;

    @Json(name = "original_title")
    public final String originalTitle;

    @Json(name = "original_language")
    public final String originalLanguage;

    public final String title;

    @Json(name = "backdrop_path")
    public final String backdropPath;

    public final double popularity;

    @Json(name = "vote_count")
    public final int voteCount;

    public final boolean video;

    @Json(name = "vote_average")
    public final float voteAverage;

    public Movie(String posterPath, boolean adult, String overview, Date releaseDate,
                 int[] genreIds, long id, String originalTitle, String originalLanguage,
                 String title, String backdropPath, double popularity, int voteCount,
                 boolean video, float voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    /**
     * Returns poster path absolute url.
     *
     * @return poster pah url
     */
    public String getPosterUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    /**
     * Returns backdrop path absolute url.
     *
     * @return backdrop pah url
     */
    public String getBackdropUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("posterPath='").append(posterPath).append('\'');
        sb.append(", adult=").append(adult);
        sb.append(", overview='").append(overview).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", genreIds=").append(Arrays.toString(genreIds));
        sb.append(", id=").append(id);
        sb.append(", originalTitle='").append(originalTitle).append('\'');
        sb.append(", originalLanguage='").append(originalLanguage).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", backdropPath='").append(backdropPath).append('\'');
        sb.append(", popularity=").append(popularity);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", video=").append(video);
        sb.append(", voteAverage=").append(voteAverage);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Movie date adapter class.
     */
    public static class DateAdapter {

        private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        @ToJson
        String toJson(Date date) {
            return dateFormatter.format(date);
        }

        @FromJson
        Date fromJson(String date) throws ParseException {
            return dateFormatter.parse(date);
        }
    }
}
