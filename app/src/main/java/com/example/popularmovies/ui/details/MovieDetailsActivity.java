package com.example.popularmovies.ui.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.popularmovies.R;
import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;


public class MovieDetailsActivity extends AppCompatActivity {
    private TextView tvTitle, tvLanguage, tvOverview, tvReleaseDate, tvRating;
    private ImageView ivPoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        tvTitle = findViewById(R.id.titleTextView);
        tvLanguage = findViewById(R.id.languageTextView);
        tvOverview = findViewById(R.id.overviewTextView);
        tvRating = findViewById(R.id.ratingTextView);
        tvReleaseDate = findViewById(R.id.releaseDateTextView);
        ivPoster = findViewById(R.id.imageView);

        int movieId = getIntent().getIntExtra("movie_id", -1);
        MovieDetailsViewModel movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        movieDetailViewModel.getMovieById(movieId).observe(this, this::updateView);
    }

    private void updateView(Movie movie) {
        if (movie != null) {
            tvTitle.setText(movie.getTitle());
            tvLanguage.setText(movie.getOriginal_language());
            tvOverview.setText(movie.getOverview());
            String releaseDate = "Release date: " + movie.getRelease_date();
            tvReleaseDate.setText(releaseDate);
            String rating = "Rating: " + movie.getVote_average();
            tvRating.setText(rating);

            Picasso.get().load(Constants.IMAGE_PATH + movie.getPoster_path()).into(ivPoster);
        }
    }
}
