package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.catsafe2.R;

public class TelaInicial extends AppCompatActivity {

    ImageButton cadastrarPet, despejarRacao, historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        cadastrarPet = findViewById(R.id.imageButton_pata);
        despejarRacao = findViewById(R.id.imageButton_tijela);
        historico = findViewById(R.id.imageButton_historico);

        cadastrarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastrarPet.class));
                finish();
            }
        });
        despejarRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FormaDeDespejo.class));
                finish();
            }
        });

        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Historico.class));
                finish();
            }
        });


}
}