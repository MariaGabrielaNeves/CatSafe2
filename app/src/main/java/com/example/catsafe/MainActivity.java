package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCadastro = findViewById(R.id.textView_cadastro);
        Button button_continuar = findViewById(R.id.button_continuar);

        // Clique aqui - cadastro
        textViewCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this, Cadastro.class);
                startActivity(it);
            }
        });

        // Ação para o botão "Continuar"
        button_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Coloque aqui a ação desejada para o botão "Continuar"
            }
        });

    }
}
