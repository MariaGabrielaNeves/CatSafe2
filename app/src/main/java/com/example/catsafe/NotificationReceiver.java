package com.example.catsafe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Exibe a notificação quando o alarme é recebido
        MyNotificationManager notificationManager = new MyNotificationManager(context);
        notificationManager.sendNotification("Hora do Alerta", "É hora de revisar sua rotina diária!");
    }
}
