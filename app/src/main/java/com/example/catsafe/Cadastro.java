package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catsafe2.R;

public class Cadastro extends AppCompatActivity {
    Button btn_continuar;
    EditText email, nomeUsuario, senha, confirmaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btn_continuar = findViewById(R.id.button2_continuar);
        email = findViewById(R.id.editTextText_email);
        nomeUsuario = findViewById(R.id.editTextText_nomeUsario);
        senha = findViewById(R.id.editTextText_senha);
        confirmaSenha = findViewById(R.id.editTextText_confirmaSenha);

        btn_continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                String emailV = email.getText().toString();
                String username = nomeUsuario.getText().toString();
                String password = senha.getText().toString();
                String confirmaPassword = confirmaSenha.getText().toString();

                if(emailV.length()==0 || username.length()==0 || password.length()==0 || confirmaPassword.length()==0){
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
            }
        });
    }
}