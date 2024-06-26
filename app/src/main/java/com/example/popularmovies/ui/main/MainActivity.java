package com.example.popularmovies.ui.main;


import static com.example.popularmovies.utils.Constants.API_KEY;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.R;
import com.example.popularmovies.utils.AlarmSetup;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        createNotificationChannel();
        AlarmSetup.scheduleDailyMovieNotification(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, movies -> {
            adapter = new MovieAdapter(MainActivity.this, movies);
            recyclerView.setAdapter(adapter);
        });


        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

        movieViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        movieViewModel.fetchMoviesFromApi(API_KEY, 1);
    }


    private void createNotificationChannel() {
        CharSequence name = "Daily Movie Alerts";
        String description = "Notifications for daily movie suggestions";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("movie_channel", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }
}
