package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;

public class LoginActivity extends AppCompatActivity {
    EditText nomeUsuario, senha;
    Button btn_continuar;
    TextView esqueceuSenha, cadastro;
    ImageButton olhoSenha;
    CheckBox checkBox2;
    private DatabaseHelper db;
    private SharedPreferences sharedPreferences; // Declarando o SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nomeUsuario = findViewById(R.id.TextUsername);
        senha = findViewById(R.id.editTextText_senha);
        btn_continuar = findViewById(R.id.button_continuar2);
        //esqueceuSenha = findViewById(R.id.textView_esqueceusenha);
        cadastro = findViewById(R.id.textView_cadastro);
        olhoSenha = findViewById(R.id.imageButton3_olhoAberto);
        checkBox2 = findViewById(R.id.checkBox2);

        db = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        nomeUsuario.setText(savedUsername);
        senha.setText(savedPassword);

        if (sharedPreferences.getBoolean("save_login", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        // Mostrar ou ocultar senha
        olhoSenha.setOnClickListener(new View.OnClickListener() {
            boolean senhaVisivel = false;
            public void onClick(View view) {
                senha.setInputType(senhaVisivel
                        ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                        : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                senha.setSelection(senha.length());
                olhoSenha.setImageResource(senhaVisivel ? R.drawable.olhofechado : R.drawable.olhoaberto);
                senhaVisivel = !senhaVisivel;
            }
        });

        // Lógica do botão de login
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username = nomeUsuario.getText().toString();
                String password = senha.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("LoginActivity", "Verificando usuário: " + username);
                    if (db.checarUsuario(username, password)) {
                        Log.d("LoginActivity", "Usuário autenticado com sucesso");
                        String email = db.getUsuario(username);
                        if (email != null) {
                            Log.d("LoginActivity", "Email recuperado: " + email);
                        } else {
                            Log.e("LoginActivity", "Email não encontrado para o usuário: " + username);
                        }

//                        int userId = db.getUserIdById(username); // Aqui você obtém o userId associado ao username
//
//                        if (userId != -1) {
//                            Log.d("LoginActivity", "userId recuperado: " + userId);
//                        } else {
//                            Log.e("LoginActivity", "Erro ao recuperar userId para o usuário: " + username);
//                        }


                        // Salvando as preferências de login
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("userEmail", email);
//                        editor.putInt("userId", userId);

                        if (checkBox2.isChecked()) {
                            editor.putString("password", password);
                            editor.putBoolean("save_login", true);
                        } else {
                            editor.remove("password");
                            editor.putBoolean("save_login", false);
                        }

                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.d("LoginActivity", "Usuário não existe");
                        Toast.makeText(getApplicationContext(), "Usuário não existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Redireciona para a tela de cadastro
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
                finish();
            }
        });
    }
}
