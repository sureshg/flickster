package io.sureshg.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.sureshg.flicks.mdb.Movie;

/**
 * Movies list view adapter.
 */
public class MoviesArrayAdapter extends ArrayAdapter<Movie> {

    /**
     * Constructor
     *
     * @param context The current context.
     * @param movies  {@link Movie} list.
     */
    public MoviesArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Movie movie = getItem(position);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.ivMovieImage.setImageResource(0);
        holder.tvTitle.setText(movie.title);
        holder.tvOverview.setText(movie.overview);

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            Picasso.with(getContext()).load(movie.getBackdropUrl()).into(holder.ivMovieImage);
        } else {
            Picasso.with(getContext()).load(movie.getPosterUrl()).into(holder.ivMovieImage);
        }

        return view;
    }

    /**
     * View holder class
     */
    static class ViewHolder {

        @BindView(R.id.ivMovieImage)
        ImageView ivMovieImage;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvOverview)
        TextView tvOverview;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
