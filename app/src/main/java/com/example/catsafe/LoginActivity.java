package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catsafe2.R;

public class LoginActivity extends AppCompatActivity {

    EditText nomeUsuario, senha;
    Button btn_continuar;
    TextView esqueceuSenha, cadastro;
    ImageButton olhoSenha;
    CheckBox checkBox2;
    private DataBaseHelper db;

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
        checkBox2 = findViewById(R.id.checkBox2);

        db = new DataBaseHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Verifica se o login está salvo e se os dados estão disponíveis
        boolean saveLogin = sharedPreferences.getBoolean("save_login", false);
        if (saveLogin) {
            String savedUsername = sharedPreferences.getString("username", null);
            String savedPassword = sharedPreferences.getString("password", null);

            if (savedUsername != null && savedPassword != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return; // Termina o onCreate() para não continuar executando o restante do código
            }
        }

        // Olhinho - show/hide senha
        olhoSenha.setOnClickListener(new View.OnClickListener() {
            boolean senhaVisivel = false;

            public void onClick(View view) {
                if (!senhaVisivel) {
                    senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    olhoSenha.setImageResource(R.drawable.olhoaberto);
                } else {
                    senha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    senha.setSelection(senha.length());
                    olhoSenha.setImageResource(R.drawable.olhofechado);
                }
                senhaVisivel = !senhaVisivel;
            }
        });

        // Botão Continuar
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username = nomeUsuario.getText().toString();
                String password = senha.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.checkUser(username, password)) {
                        // Login bem-sucedido
                        String email = db.getUserEmail(username); // Suponha que o método existe no DataBaseHelper

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", username);
                        editor.putString("userEmail", email); // Salva o email

                        if (checkBox2.isChecked()) {
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putBoolean("save_login", true);
                        } else {
                            // Remover dados
                            editor.remove("username");
                            editor.remove("password");
                            editor.putBoolean("save_login", false);
                        }
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Credenciais incorretas
                        Toast.makeText(getApplicationContext(), "Usuário não existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
                finish();
            }
        });

        // Preencher campos se o login estiver salvo
        if (saveLogin) {
            nomeUsuario.setText(sharedPreferences.getString("username", ""));
            senha.setText(sharedPreferences.getString("password", ""));
            checkBox2.setChecked(true);
        }
    }
}
