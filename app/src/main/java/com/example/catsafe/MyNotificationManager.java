package com.example.catsafe;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.catsafe2.R;

public class MyNotificationManager {
    private Context context;

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    public void sendNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24) // Substitua com o ícone da sua notificação
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Para Android 8.0 e superior, criar um canal de notificação
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NotificationHelper.CHANNEL_ID,
                    NotificationHelper.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(NotificationHelper.CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }

        // Exibir a notificação
        notificationManager.notify(1, builder.build());
    }
}
