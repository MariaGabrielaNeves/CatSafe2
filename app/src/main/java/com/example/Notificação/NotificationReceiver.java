package com.example.Notificação;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.catsafe.FormaDeDespejoActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Exibe a notificação quando o alarme é recebido
        MyNotificationManager notificationManager = new MyNotificationManager(context);
        notificationManager.sendNotification("Hora do Alerta", "É hora de revisar sua rotina diária!");

        // Move o servo para o ângulo inicial
        new MoveServo().execute(120);

        // Após 3 segundos, move o servo para o ângulo final
        new Handler().postDelayed(() -> new MoveServo().execute(150), 3000);
    }

    // Classe MoveServo que controla o movimento do servo
    private static class MoveServo extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... angles) {
            try {
                // URL do servo motor com o parâmetro de ângulo
                String servoUrl = "http://10.100.51.50/rotate-servo?angle=" + angles[0];
                HttpURLConnection connection = (HttpURLConnection) new URL(servoUrl).openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = connection.getInputStream();
                    // Aqui você pode processar o fluxo de dados, se necessário.
                    in.close(); // Não se esqueça de fechar o fluxo.
                }
            } catch (Exception e) {
                e.printStackTrace(); // Registra o erro caso ocorra
            }
            return null;
        }
    }
}

