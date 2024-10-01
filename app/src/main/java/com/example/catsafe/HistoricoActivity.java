package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.ListView.HistoricoAdpt;
import com.example.ListView.MyAdapter;
import com.example.catsafe2.R;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    ArrayList<HistoricoAdpt> historicoAdpts = new ArrayList<>();
    HistoricoAdpt historicoAdpt2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        historicoAdpts.addAll(inicializarHistorico());
        context = this;

        myAdapter = new MyAdapter(historicoAdpts, this);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(myAdapter);

        ImageButton voltar = findViewById(R.id.imageButton2_voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private List<HistoricoAdpt> inicializarHistorico() {
            List<HistoricoAdpt> listaDeProdutos = new ArrayList<>();

            listaDeProdutos.add(new HistoricoAdpt("Hoje", 3));
            listaDeProdutos.add(new HistoricoAdpt("Ontem", 2));
            listaDeProdutos.add(new HistoricoAdpt("14/08", 5));
            listaDeProdutos.add(new HistoricoAdpt("13/08", 1));
            listaDeProdutos.add(new HistoricoAdpt("12/08", 4));

            return listaDeProdutos;
    }

}

