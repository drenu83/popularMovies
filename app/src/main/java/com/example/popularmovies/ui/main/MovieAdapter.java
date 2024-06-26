package com.example.popularmovies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.R;
import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.ui.details.MovieDetailsActivity;
import com.example.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> moviesList;
    private final List<Movie> filteredMovies;
    private final Context context;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.moviesList = movieList;
        this.filteredMovies = new ArrayList<>(movieList);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = filteredMovies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvLanguage.setText(movie.getOriginal_language());
        holder.tvRating.setText(String.valueOf(movie.getPopularity()));

        Picasso.get().load(Constants.IMAGE_PATH + movie.getPoster_path()).into(holder.ivPoster);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie_id", movie.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredMovies.size();
    }


    public void filter(String query) {
        List<Movie> newFilteredMovies = new ArrayList<>();
        if (query == null || query.isEmpty()) {
            newFilteredMovies.addAll(new ArrayList<>(moviesList));
        } else {
            query = query.trim().toLowerCase();
            for (Movie movie : filteredMovies) {
                String title = movie.getTitle().trim().toLowerCase();
                if (title.contains(query)) {
                    newFilteredMovies.add(movie);
                }
            }
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MovieDiffCallback(filteredMovies, newFilteredMovies));
        filteredMovies.clear();
        filteredMovies.addAll(newFilteredMovies);
        diffResult.dispatchUpdatesTo(this);
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLanguage, tvRating;
        ImageView ivPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleTextView);
            tvLanguage = itemView.findViewById(R.id.languageTextView);
            tvRating = itemView.findViewById(R.id.ratingTextView);
            ivPoster = itemView.findViewById(R.id.imageView);
        }
    }

}


