package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.catsafe2.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FormaDeDespejoActivity extends AppCompatActivity {
    Dialog dialog;
    ImageButton voltar, imageButton_programar, imageButton_despejar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_de_despejo);

        voltar = findViewById(R.id.imageButton2_voltar);
        imageButton_programar = findViewById(R.id.imageButton_programar);
        imageButton_despejar = findViewById(R.id.imageButton_despejar);

        dialog = new Dialog(this);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        imageButton_despejar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveServo moveServo = new MoveServo();
                moveServo.execute(120);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MoveServo moveServo2 = new MoveServo();
                        moveServo2.execute(150);
                    }
                }, 3000);
                openDialog();
            }
        });

        imageButton_programar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormaDeDespejoActivity.this, ProgramarHorarioActivity.class));
            }
        });
    }

    private void openDialog() {
        dialog.setContentView(R.layout.activity_racao_despejada_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageButton imageButton_fechar = dialog.findViewById(R.id.imageButton_fechar);
        imageButton_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    class MoveServo extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... angles) {

            for (int i = 0; i < 1; i++) {
                try {
                    URL url = new URL("http://10.100.51.50/rotate-servo?angle=" + String.valueOf(angles[0]));
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                    conexao.setReadTimeout(5000);
                    conexao.setConnectTimeout(5000);
                    conexao.setRequestMethod("GET");
                    conexao.setDoInput(true);
                    conexao.setDoOutput(false);
                    conexao.connect();
                    if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = conexao.getInputStream();
                    }
                } catch (Exception e) {
                }
            }
            return null;

        }
    }
}