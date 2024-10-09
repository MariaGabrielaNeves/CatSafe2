package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.catsafe2.R;

public class ConectarActivity extends AppCompatActivity {
    Button button2_continuar;
    TextView textView_cadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectar);

        button2_continuar = findViewById(R.id.button2_continuar);
        textView_cadastro = findViewById(R.id.textView_cadastro);

        button2_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConectarActivity.this, LoginActivity.class));
            }
        });

        textView_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConectarActivity.this, CadastroActivity.class));
            }
        });

    }
}