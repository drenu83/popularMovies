package com.example.popularmovies.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.data.repository.MovieRepository;

public class MovieDetailsViewModel extends AndroidViewModel {
    private final MovieRepository repository;
    private LiveData<Movie> movieLiveData;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<Movie> getMovieById(int id) {
        movieLiveData = repository.getMovieById(id);
        return movieLiveData;
    }
}
