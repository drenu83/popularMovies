package com.example.popularmovies.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.popularmovies.data.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MovieDatabase.class, "movies_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
