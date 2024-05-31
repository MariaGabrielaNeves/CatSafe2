package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catsafe2.R;

public class Login extends AppCompatActivity {
    EditText nomeUsuario, senha;
    Button btn_continuar;
    TextView esqueceuSenha, cadastro;
    ImageButton olhoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nomeUsuario = findViewById(R.id.editTextText_nomeUsario);
        senha = findViewById(R.id.editTextText_senha);
        btn_continuar = findViewById(R.id.button_continuar2);
        esqueceuSenha = findViewById(R.id.textView_esqueceusenha);
        cadastro = findViewById(R.id.textView_cadastro);
        olhoSenha = findViewById(R.id.imageButton3_olhoAberto);

        //olhinho - show/hide senha - working
        olhoSenha.setOnClickListener(new View.OnClickListener() {
            boolean senhaVisivel = false;
            public void onClick (View view){
                if(!senhaVisivel){
                    senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    olhoSenha.setImageResource(R.drawable.olhoaberto);
                }else {
                    senha.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    senha.setSelection(senha.length());
                    olhoSenha.setImageResource(R.drawable.olhofechado);
                }
                senhaVisivel = !senhaVisivel;
            }
        });

        //botão Continuar - working
        btn_continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                String username = nomeUsuario.getText().toString();
                String password = senha.getText().toString();
                if(username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Login.this, TelaInicial.class);
                    startActivity(intent);
                }
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Cadastro.class));
                    finish();
            }
        });

    }
}