package com.example.Notificação;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationHelper {
    public static final String ID_CANAL = "my_channel_id";
    public static final CharSequence NOME_CANAL = "CatSafe_not";
    public static final String DESCRICAO_CANAL = "Notificação sobre gatos";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    ID_CANAL,
                    NOME_CANAL,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(DESCRICAO_CANAL);

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
