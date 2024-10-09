package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.catsafe2.R;

public class DadosCadastradosActivity extends AppCompatActivity {

    Button alterarInformacoes;
    ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrados);

        alterarInformacoes = findViewById(R.id.alterarInformacoes);
        voltar = findViewById(R.id.imageButton2_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        alterarInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DadosCadastradosActivity.this, AlterarInformacaoActivity.class));
            }
        });
    }
}