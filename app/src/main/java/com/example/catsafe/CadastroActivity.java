package com.example.catsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bd.DatabaseHelper;
import com.example.bd.Usuario;
import com.example.catsafe2.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity {
    Button btn_continuar;
    TextView entrar;
    ImageButton olhoSenha;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        TextInputLayout textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        TextInputEditText textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        TextInputLayout textInputLayoutNomeUsuario = findViewById(R.id.textInputLayoutNomeUsuario);
        TextInputEditText textInputEditNomeUsuario = findViewById(R.id.textInputEditNomeUsuario);
        TextInputLayout textInputLayoutSenha = findViewById(R.id.textInputLayoutSenha);
        TextInputEditText textInputEditSenha = findViewById(R.id.textInputEditSenha);
        TextInputLayout textInputEditLayoutConfirmaSenha = findViewById(R.id.textInputLayoutConfirmaSenha);
        TextInputEditText textInputEditTextConfirmaSenha = findViewById(R.id.textInputEditTextConfirmaSenha);
        //olhoSenha = findViewById(R.id.imageButton3_olhoAberto);
        btn_continuar = findViewById(R.id.button2_continuar);
        entrar = findViewById(R.id.textView_cadastro);

        db = DatabaseHelper.getDatabase(this);


        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            //sem uso
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(isValidEmail(s.toString())){
                    textInputLayoutEmail.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            //sem uso
            public void afterTextChanged(Editable s) {

            }
        });

        btn_continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                String emailV = textInputEditTextEmail.getText().toString().trim();
                String name = textInputEditNomeUsuario.getText().toString().trim();
                String password = textInputEditSenha.getText().toString().trim();
                String confirmaPassword = textInputEditTextConfirmaSenha.getText().toString().trim();

                if(emailV.length() == 0 || name.length() == 0 || password.length() == 0 || confirmaPassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    textInputLayoutEmail.setError("Por favor, insira um email");
                } else if (!isValidEmail(emailV)) {
                    textInputLayoutEmail.setError("Email inválido");
                } else if (!password.equals(confirmaPassword)) {
                    textInputLayoutSenha.setError("As senhas não coincidem");
                } else if (db.getUsuarioDAO().isInserted(name, emailV, password)) {
                    Toast.makeText(CadastroActivity.this, "Usuário já cadastrado com essas informações!", Toast.LENGTH_SHORT).show();
                } else {
                    Usuario novoUsuario = new Usuario(name, emailV, password);
                    db.getUsuarioDAO().addUsuario(novoUsuario);
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                    finish();
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


    public boolean isValidEmail(String email){
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}