package com.example.popularmovies.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.data.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private final MovieRepository repository;
    private final LiveData<List<Movie>> movies;
    private final LiveData<String> errorMessage;

    public MovieViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
        movies = repository.getMoviesFromDb();
        errorMessage = repository.getErrorMessage();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void fetchMoviesFromApi(String apiKey, int page) {
        repository.fetchMoviesFromApi(apiKey, page);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}





