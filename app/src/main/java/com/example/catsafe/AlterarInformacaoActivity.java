package com.example.catsafe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;

public class AlterarInformacaoActivity extends AppCompatActivity {
    Dialog dialog;
    ImageButton voltar;
    Button button_salvarAlteracoes;
    EditText editTextText_novoEmail, editTextText_nome, editTextText_senhaAtual, editTextText_novaSenha;
    DatabaseHelper db;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_informacao);

        dialog = new Dialog(this);
        voltar = findViewById(R.id.imageButton2_voltar);
        button_salvarAlteracoes = findViewById(R.id.button_salvarAlteracoes);
        editTextText_novoEmail = findViewById(R.id.editTextText_novoEmail);
        editTextText_nome = findViewById(R.id.editTextText_nome);
        editTextText_senhaAtual = findViewById(R.id.editTextText_senhaAtual);  // Senha atual
        editTextText_novaSenha = findViewById(R.id.editTextText_novaSenha);  // Nova senha

        db = new DatabaseHelper(this);

        // Recuperar o userId da sessão
        userId = getUserIdFromSession();


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        button_salvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    // Método para obter o userId da sessão
    private int getUserIdFromSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);  // Retorna -1 caso o ID não seja encontrado
    }


    private void updateUser() {
        String username = editTextText_nome.getText().toString();
        String email = editTextText_novoEmail.getText().toString();
        String currentPassword = editTextText_senhaAtual.getText().toString();  // Senha atual
        String newPassword = editTextText_novaSenha.getText().toString();  // Nova senha

        // Validar os campos
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Nome de usuário e e-mail são obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se a senha atual corresponde à senha no banco de dados
        if (!TextUtils.isEmpty(newPassword)) {
            if (TextUtils.isEmpty(currentPassword)) {
                Toast.makeText(this, "Por favor, insira a senha atual.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar se a senha atual está correta
            if (!db.checkUserPassword(userId, currentPassword)) {
                Toast.makeText(this, "A senha atual está incorreta.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Verificar se o nome de usuário já existe no banco de dados
        if (db.usernameExists(username) && !username.equals(editTextText_nome.getText().toString())) {
            Toast.makeText(this, "Nome de usuário já existe!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualizar os dados no banco de dados
        boolean isUpdated;
        if (TextUtils.isEmpty(newPassword)) {
            // Se a senha não foi alterada, não a atualize
            isUpdated = db.atualizarUsuario(userId, username, email, newPassword);
        } else {
            // Se a senha foi alterada, atualize todos os dados (incluindo a nova senha)
            isUpdated = db.atualizarUsuario(userId, username, email, newPassword);
        }

        if (isUpdated) {
            Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao atualizar os dados.", Toast.LENGTH_SHORT).show();
        }
    }

}

