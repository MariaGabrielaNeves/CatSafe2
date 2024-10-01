package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.catsafe2.R;

public class AlterarInformacaoActivity extends AppCompatActivity {

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_informacao);

        dialog = new Dialog(this);
        ImageButton voltar = findViewById(R.id.imageButton2_voltar);
        Button button_salvarAlteracoes = findViewById(R.id.button_salvarAlteracoes);

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
                openDialog();
            }
        });


    }
    private void openDialog(){
        dialog.setContentView(R.layout.activity_alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton imageButton_fechar=dialog.findViewById(R.id.imageButton_fechar);

        imageButton_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();

    }

}
