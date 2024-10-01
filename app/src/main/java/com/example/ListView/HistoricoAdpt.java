package com.example.ListView;

public class HistoricoAdpt {
    String dia;
    int num_refeicao;

    public HistoricoAdpt (String dia, int num_refeicao){
        this.dia=dia;
        this.num_refeicao=num_refeicao;
    }

    public String getDia() {
        return dia;
    }

    /*public void setDia(String dia) {
        this.dia = dia;
    }*/

    public int getNum_refeicao() {
        return num_refeicao;
    }

    /*public void setNum_refeicao(int num_refeicao) {
        this.num_refeicao = num_refeicao;
    }*/

}
