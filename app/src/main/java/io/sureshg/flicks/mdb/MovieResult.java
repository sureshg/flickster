package io.sureshg.flicks.mdb;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Movie result model.
 *
 * @author Suresh
 */

public final class MovieResult {

    public final int page;

    public final List<Movie> results;

    @Json(name = "total_pages")
    public final int totalPages;

    @Json(name = "total_results")
    public final int totalResults;

    public MovieResult(int page, List<Movie> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieResult{");
        sb.append("page=").append(page);
        sb.append(", results=").append(results);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", totalResults=").append(totalResults);
        sb.append('}');
        return sb.toString();
    }
}


