package com.example.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.catsafe2.R;

import java.util.ArrayList;

public class Adapter2 extends BaseAdapter {
    ArrayList<Historico2Adpt> historico2Adpts;
    Context context;

    public Adapter2(ArrayList<Historico2Adpt> historico2Adpts, Context context){
        this.historico2Adpts = historico2Adpts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return historico2Adpts.size();
    }

    @Override
    public Object getItem(int position) {
        return historico2Adpts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;  // Retorna a posição como ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Reutilize a View existente para otimizar o desempenho
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.historico_lista, parent, false);
        }

        Historico2Adpt historicoAdpt = historico2Adpts.get(position);

        TextView textView_textoNotificacao1 = convertView.findViewById(R.id.textView_textoNotificacao1);

        textView_textoNotificacao1.setText(historicoAdpt.getTexto());
        return convertView;
    }
}
