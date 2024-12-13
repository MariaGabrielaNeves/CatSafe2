package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;

public class DadosCadastradosActivity extends AppCompatActivity {
    Button alterarInformacoes;
    ImageButton voltar;
    TextView text_email, text_nomeUsuario, text_senhaAtual;
    DatabaseHelper db;
    private int userId;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrados);

        // Inicializando os componentes da interface
        alterarInformacoes = findViewById(R.id.alterarInformacoes);
        voltar = findViewById(R.id.imageButton2_voltar);
        text_email = findViewById(R.id.TextEmailAddress);
        text_nomeUsuario = findViewById(R.id.TextUsername);
        text_senhaAtual = findViewById(R.id.editTextTextPassword);

        // Inicializando a instância do banco de dados
        db = new DatabaseHelper(this);

        // Recuperar o userId da sessão
        userId = getUserIdFromSession();
        //Toast.makeText(this, "DadosCadastrados: User ID recuperado: " + userId, Toast.LENGTH_SHORT).show();

        // Carregar os dados do usuário se userId estiver válido
        if (userId != -1) {
            loadUserData();
        } else {
            Log.d("DadosCadastrados", "userId inválido. Não foi possível carregar os dados.");
        }


        // Configuração do botão Voltar
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        // Configuração do botão Alterar Informações
        alterarInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DadosCadastradosActivity.this, AlterarInformacaoActivity.class));
            }
        });
    }

    // Método para obter o userId da sessão
    private int getUserIdFromSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);  // Retorna -1 caso o ID não seja encontrado
    }

    // Método para carregar os dados do usuário
    @SuppressLint("Range")
    private void loadUserData() {
        Cursor cursor = db.getUserById(userId);
        if (cursor != null && cursor.moveToFirst()) {
            String nomeUsuario = cursor.getString(cursor.getColumnIndex(db.COLUNA_NOME_USUARIO));
            String email = cursor.getString(cursor.getColumnIndex(db.COLUNA_EMAIL_USUARIO));
            String senha = cursor.getString(cursor.getColumnIndex(db.COLUNA_SENHA_USUARIO));

            Log.d("DadosCadastrados", "Nome: " + nomeUsuario + ", Email: " + email + ", Senha: " + senha);

            text_nomeUsuario.setText(nomeUsuario);
            text_email.setText(email);
            text_senhaAtual.setText(senha);
        } else {
            //Log.d("DadosCadastrados", "Nenhum dado encontrado para o userId: " + userId);
        }
        if (cursor != null) cursor.close();
    }

}
