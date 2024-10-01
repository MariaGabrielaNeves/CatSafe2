package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catsafe2.R;

public class CadastroActivity extends AppCompatActivity {
    Button btn_continuar;
    EditText email, nomeUsuario, senha, confirmaSenha;
    TextView entrar;
    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btn_continuar = findViewById(R.id.button2_continuar);
        email = findViewById(R.id.editTextText_email);
        nomeUsuario = findViewById(R.id.editTextText_nomeUsario);
        senha = findViewById(R.id.editTextText_senha);
        confirmaSenha = findViewById(R.id.editTextText_confirmaSenha);
        entrar = findViewById(R.id.textView_cadastro);

        db = new DataBaseHelper(this);

        btn_continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                String emailV = email.getText().toString().trim();
                String name = nomeUsuario.getText().toString().trim();
                String password = senha.getText().toString().trim();
                String confirmaPassword = confirmaSenha.getText().toString().trim();

                if(emailV.length()==0 || name.length()==0 || password.length()==0 || confirmaPassword.length()==0){
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = db.addUser(name,emailV, password);
                    if(isInserted){
                        Toast.makeText(CadastroActivity.this, "Usuário cadastro!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(CadastroActivity.this, "ERROR!", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}