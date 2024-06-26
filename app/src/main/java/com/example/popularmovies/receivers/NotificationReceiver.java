package com.example.popularmovies.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.popularmovies.R;
import com.example.popularmovies.data.local.MovieDatabase;
import com.example.popularmovies.data.models.Movie;
import com.example.popularmovies.ui.main.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        new Thread(() -> {

            Movie movie = MovieDatabase.getDatabase(context).movieDao().getPopularMovie();
            if (movie != null) {
                showNotification(context, movie.getTitle(), "Check out today's most popular movie: " + movie.getTitle());
            }
        }).start();
    }

    private void showNotification(Context context, String title, String content) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "movie_channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_movie)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }
}

