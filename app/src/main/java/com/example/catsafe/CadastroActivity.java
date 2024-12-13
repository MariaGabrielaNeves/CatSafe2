package com.example.catsafe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroActivity extends AppCompatActivity {
    Button btn_continuar;
    TextView entrar;
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
        btn_continuar = findViewById(R.id.button2_continuar);
        entrar = findViewById(R.id.textView_cadastro);

        db = new DatabaseHelper(getApplicationContext());

        //colocar o erro se o email estiver errado
        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (isValidEmail(s.toString())) {
//                    textInputLayoutEmail.setError(null);
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            //sem uso
            public void afterTextChanged(Editable s) {
            }
        });
        textInputEditNomeUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 5) {
                    textInputLayoutNomeUsuario.setError(null);
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
        textInputEditSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 5) {
                    textInputLayoutNomeUsuario.setError(null);
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
        textInputEditTextConfirmaSenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String senha = textInputEditSenha.getText().toString().trim();
                String confirmaSenha = s.toString().trim();

                // Comparar as senhas
                if (!confirmaSenha.equals(senha)) {
                    textInputEditTextConfirmaSenha.setError("As senhas não coincidem");
                } else {
                    textInputEditTextConfirmaSenha.setError(null);
                }
            }
        });

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String emailV = textInputEditTextEmail.getText().toString().trim();
                String nome = textInputEditNomeUsuario.getText().toString().trim();
                String senha = textInputEditSenha.getText().toString().trim();
                String confirmaSenha = textInputEditTextConfirmaSenha.getText().toString().trim();

//                if (validarCadastro(emailV, nome, senha, confirmaSenha)) {
//                    boolean isInserted = db.adicionarUsuario(nome, emailV, senha);
//                    if (isInserted) {
                Toast.makeText(CadastroActivity.this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                finish();
//                    } else {
//                        Toast.makeText(CadastroActivity.this, "Usuário já cadastrado com esse nome ou email!", Toast.LENGTH_LONG).show();

            }
        });

//            private boolean validarCadastro(String emailV, String nome, String senha, String confirmaSenha) {
//
//                //campos vazios
//                if (emailV.length() == 0 || nome.length() == 0 || senha.length() == 0 || confirmaSenha.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//                Log.d("CadastroActivity", "Email: " + emailV + ", Nome: " + nome + ", Senha: " + senha + ", ConfirmaSenha: " + confirmaSenha);
//
//                if (!isValidEmail(emailV)) {
//                    textInputLayoutEmail.setError("Email inválido");
//                    return false;
//                }
//
//                if (nome.length() < 5) {
//                    textInputLayoutNomeUsuario.setError("O nome deve conter mais de 5 caracteres");
//                    return false;
//                }
//
//                if (senha.length() == 5) {
//                    textInputLayoutSenha.setError("A senha deve conter mais de 5 caracteres!");
//                    return false;
//                }
//
//                return true;
//            }
//        });
//
//        entrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
////                finish()
//                } catch (Exception e) {
//                    Log.e("CadastroActivity", "Erro ao cadastrar: " + e.getMessage());
//                    Toast.makeText(CadastroActivity.this, "Ocorreu um erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public boolean isValidEmail(String email) {
//        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//}
    }
}