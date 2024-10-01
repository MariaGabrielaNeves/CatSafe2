package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.catsafe2.R;

public class FormaDeDespejoActivity extends AppCompatActivity {
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_de_despejo);

        ImageButton voltar, imageButton2, imageButton;
        dialog = new Dialog(this);

        voltar = findViewById(R.id.imageButton2_voltar);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton = findViewById(R.id.imageButton);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormaDeDespejoActivity.this, ProgramarHorarioActivity.class));
            }
        });


    }
    private void openDialog(){
        dialog.setContentView(R.layout.activity_racao_despejada_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton imageButton_fechar = dialog.findViewById(R.id.imageButton_fechar);

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