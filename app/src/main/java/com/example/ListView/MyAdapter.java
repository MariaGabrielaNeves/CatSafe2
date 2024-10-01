package com.example.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.catsafe2.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    ArrayList<HistoricoAdpt> historicoAdpts;
    Context context;

    public MyAdapter(ArrayList<HistoricoAdpt> historicoAdpts, Context context){
        this.historicoAdpts=historicoAdpts;
        this.context=context;
    }

    @Override
    public int getCount() {
        return historicoAdpts.size();
    }

    @Override
    public Object getItem(int position) {
        return historicoAdpts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoricoAdpt historicoAdpt=historicoAdpts.get(position);
        View v = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);

        TextView textView6_numRefeicaoHoje = v.findViewById(R.id.textView_num_refeicao2);
        TextView textView4_dia = v.findViewById(R.id.textView1_data);

        textView4_dia.setText(historicoAdpt.getDia());
        textView6_numRefeicaoHoje.setText(String.valueOf(historicoAdpt.getNum_refeicao()));
        return v;
    }
}
