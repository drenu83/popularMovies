package com.example.popularmovies.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.popularmovies.data.local.MovieDao;
import com.example.popularmovies.data.local.MovieDatabase;
import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.data.models.MovieResponse;
import com.example.popularmovies.data.remote.MovieApiService;
import com.example.popularmovies.data.remote.RetrofitInstance;
import com.example.popularmovies.utils.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String TAG = "MovieRepository";
    private final MovieApiService apiService;
    private final MovieDao movieDao;
    Application application;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public MovieRepository(Application application) {
        MovieDatabase db = MovieDatabase.getDatabase(application);
        this.application = application;
        movieDao = db.movieDao();
        apiService = RetrofitInstance.getRetrofitInstance().create(MovieApiService.class);
    }

    public LiveData<List<Movie>> getMoviesFromDb() {
        return movieDao.getAllMovies();
    }

    public void fetchMoviesFromApi(String apiKey, int page) {
        if (!NetworkUtils.isNetworkAvailable(application)) {
            errorMessage.postValue("No internet connection! Loading local data...");
            return;
        }

        apiService.getPopularMovies(apiKey, page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    new Thread(() -> movieDao.insertAll(movies)).start();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }


    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Movie> getMovieById(int id) {
        return movieDao.getMovieById(id);
    }
}



