//package com.example.Notificação;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.content.Context;
//import android.os.Build;
//
//import androidx.core.app.NotificationCompat;
//
//import com.example.catsafe2.R;
//
//public class MyNotificationManager {
//    private Context context;
//
//    public MyNotificationManager(Context context) {
//        this.context = context;
//    }
//
//    public void sendNotification(String title, String message) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.ID_CANAL)
//                .setSmallIcon(R.drawable.baseline_notifications_active_24)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    NotificationHelper.ID_CANAL,
//                    NotificationHelper.NOME_CANAL,
//                    NotificationManager.IMPORTANCE_DEFAULT
//            );
//            channel.setDescription(NotificationHelper.DESCRICAO_CANAL);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        // Exibição da notificação
//        notificationManager.notify(1, builder.build());
//    }
//}
