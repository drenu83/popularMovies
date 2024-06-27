package com.example.popularmovies.ui.main;

import androidx.recyclerview.widget.DiffUtil;

import com.example.popularmovies.data.models.Movie;

import java.util.List;

class MovieDiffCallback extends DiffUtil.Callback {
    private final List<Movie> oldMovies;
    private final List<Movie> newMovies;

    public MovieDiffCallback(List<Movie> oldMovies, List<Movie> newMovies) {
        this.oldMovies = oldMovies;
        this.newMovies = newMovies;
    }

    @Override
    public int getOldListSize() {
        return oldMovies.size();
    }

    @Override
    public int getNewListSize() {
        return newMovies.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

        return oldMovies.get(oldItemPosition).getId() == newMovies.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

        Movie oldMovie = oldMovies.get(oldItemPosition);
        Movie newMovie = newMovies.get(newItemPosition);

        return oldMovie.getTitle().equals(newMovie.getTitle()) &&
                oldMovie.getOriginal_language().equals(newMovie.getOriginal_language()) &&
                oldMovie.getPopularity() == newMovie.getPopularity() &&
                oldMovie.getPoster_path().equals(newMovie.getPoster_path());
    }
}
