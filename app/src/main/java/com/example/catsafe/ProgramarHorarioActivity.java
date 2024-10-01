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

public class ProgramarHorarioActivity extends AppCompatActivity {
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programar_horario);

        ImageButton voltar;
        Button button2_continuar;
        dialog = new Dialog(this);

        voltar = findViewById(R.id.imageButton2_voltar);
        button2_continuar = findViewById(R.id.button2_continuar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        button2_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    private void openDialog(){
        dialog.setContentView(R.layout.activity_alert_dialog);
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