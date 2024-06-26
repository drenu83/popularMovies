package com.example.popularmovies.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.popularmovies.data.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Movie> movies);

    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<Movie> getMovieById(int id);

    @Query("SELECT * FROM movies ORDER BY popularity DESC LIMIT 1")
    Movie getPopularMovie();
}
