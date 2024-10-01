package com.example.catsafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

    Button button1, button2;
    ImageView imageView;
    ImageButton voltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FotoDownload fotoDownload = new FotoDownload();
                fotoDownload.execute();
            }
        });
    }

    class FotoDownload extends AsyncTask<Integer, Void, Bitmap[]> {
        @Override
        protected void onPostExecute(Bitmap[] foto) {
            super.onPostExecute(foto);
            imageView.setImageBitmap(foto[0]);
        }

        @Override
        protected Bitmap[] doInBackground(Integer[] codFuncionarios) {
            Bitmap[] fotos = new Bitmap[1];
            for (int i = 0; i < 1; i++) {
                //int codFuncionario = codFuncionarios[i].intValue();
                //Log.d("Codigo Funcionario", "" + codFuncionario);
                try {
                    URL url = new URL("http://10.100.51.39/saved-photo");
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
                        Log.d("EntradaDeDados", "Problema para receber os dados!");
                    }
                } catch (Exception e) {
                    fotos[0] = null;
                    Log.d("EntradaDeDados", "Problema para receber os dados!");
                }
            }
            return fotos;
        }
    }

}