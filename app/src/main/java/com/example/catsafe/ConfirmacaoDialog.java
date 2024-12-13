package com.example.catsafe;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.catsafe2.R;

public class ConfirmacaoDialog extends AppCompatActivity {
    Button button_sim, button_nao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_dialog);


        button_sim = findViewById(R.id.button_sim);
        button_nao = findViewById(R.id.button_nao);

        button_sim.setOnClickListener(v -> {
            logout();
        });

        button_nao.setOnClickListener(v -> finish());

    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.putBoolean("save_login", false);
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finaliza a MainActivity após o logout
    }

}
