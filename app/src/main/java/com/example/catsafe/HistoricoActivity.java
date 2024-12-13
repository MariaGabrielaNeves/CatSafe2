package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.example.ListView.HistoricoAdpt;
import com.example.ListView.MyAdapter;
import com.example.bd.DatabaseHelper;
import com.example.catsafe2.R;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private ArrayList<String> historicoHorarios;
    private ArrayAdapter<String> adapter;
    Context context;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        android.widget.ListView listViewHistorico = findViewById(R.id.listViewHistorico); // Corrigido
        db = new DatabaseHelper(this);
        historicoHorarios = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historicoHorarios);
        listViewHistorico.setAdapter(adapter);

        carregarHorariosDoBanco();

        ImageButton voltar = findViewById(R.id.imageButton2_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void carregarHorariosDoBanco() {
        Cursor cursor = db.getAllHorarios();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String horario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUNA_HORARIO_ALIMENTACAO));
                historicoHorarios.add("Clique em: " + horario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

}

