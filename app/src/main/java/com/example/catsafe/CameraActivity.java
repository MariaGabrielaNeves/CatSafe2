package com.example.catsafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.catsafe2.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CameraActivity extends AppCompatActivity {
    Button buttonPegar, buttonCapturar;
    ImageView voltar, imageView_cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView_cam = findViewById(R.id.imageView_cam);
        buttonPegar = findViewById(R.id.button_pegar);
        buttonCapturar = findViewById(R.id.button_capturar);
        voltar = findViewById(R.id.imageButton2_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        //exibir foto
        buttonPegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FotoDownload fotoDownload = new FotoDownload();
                fotoDownload.execute();
            }
        });

        //capturar foto
        buttonCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FotoCapture fotoCapture = new FotoCapture();
                fotoCapture.execute();
            }
        });
    }

    class FotoCapture extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Bitmap[] fotos = new Bitmap[1];
            for (int i = 0; i < 1; i++) {
                try {
                    URL url = new URL("http://10.100.51.50/capture");
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                    conexao.setReadTimeout(5000);
                    conexao.setConnectTimeout(5000);
                    conexao.setRequestMethod("GET");
                    conexao.setDoInput(true);
                    conexao.setDoOutput(false);

                    conexao.connect();
                    if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = conexao.getInputStream();
                    } else {
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void foto) {
            super.onPostExecute(foto);
        }
    }

    class FotoDownload extends AsyncTask<Integer, Void, Bitmap[]> {
        @Override
        protected void onPostExecute(Bitmap[] foto) {
            super.onPostExecute(foto);
            imageView_cam.setImageBitmap(foto[0]);
        }

        @Override
        protected Bitmap[] doInBackground(Integer[] codFuncionarios) {

            Bitmap[] fotos = new Bitmap[1];
            for (int i = 0; i < 1; i++) {
                try {
                    URL url = new URL("http://10.100.51.50/saved-photo");
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                    conexao.setReadTimeout(5000);
                    conexao.setConnectTimeout(5000);
                    conexao.setRequestMethod("GET");
                    conexao.setDoInput(true);
                    conexao.setDoOutput(false);

                    conexao.connect();
                    if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = conexao.getInputStream();
                        Bitmap foto = BitmapFactory.decodeStream(in);
                        fotos[0] = foto;
                    } else {
                        fotos[0] = null;
                    }
                } catch (Exception e) {
                    fotos[0] = null;
                }
            }
            return fotos;
        }
    }

}