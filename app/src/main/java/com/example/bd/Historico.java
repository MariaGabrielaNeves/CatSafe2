package com.example.bd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Historico")
public class Historico {

    @ColumnInfo(name = "idHistorico")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int idHistorico;

    //horario em que o usuario quer que a ração caia
    @ColumnInfo(name = "horarioCadastrado")
    @NonNull
    String horarioCadastrado;

    //horario em que a ração foi despejada
    @ColumnInfo(name = "horarioDespejo")
    @NonNull
    String horarioDespejo;

    //quantidade que o usuário quer que seja despejada
    @ColumnInfo(name = "quantidadeDespejo")
    @NonNull
    int quantidadeDespejo;

    //quantidade que ainda tem no comedouro
    @ColumnInfo(name = "quantidadeComedouro")
    @NonNull
    int quantidadeComedouro;

    @Ignore
    public Historico(){
    }

    public Historico(int idHistorico, @NonNull String horarioCadastrado, @NonNull String horarioDespejo, int quantidadeDespejo, int quantidadeComedouro) {
        this.idHistorico = 0;
        this.horarioCadastrado = horarioCadastrado;
        this.horarioDespejo = horarioDespejo;
        this.quantidadeDespejo = quantidadeDespejo;
        this.quantidadeComedouro = quantidadeComedouro;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    @NonNull
    public String getHorarioCadastrado() {
        return horarioCadastrado;
    }

    public void setHorarioCadastrado(@NonNull String horarioCadastrado) {
        this.horarioCadastrado = horarioCadastrado;
    }

    @NonNull
    public String getHorarioDespejo() {
        return horarioDespejo;
    }

    public void setHorarioDespejo(@NonNull String horarioDespejo) {
        this.horarioDespejo = horarioDespejo;
    }

    public int getQuantidadeDespejo() {
        return quantidadeDespejo;
    }

    public void setQuantidadeDespejo(int quantidadeDespejo) {
        this.quantidadeDespejo = quantidadeDespejo;
    }

    public int getQuantidadeComedouro() {
        return quantidadeComedouro;
    }

    public void setQuantidadeComedouro(int quantidadeComedouro) {
        this.quantidadeComedouro = quantidadeComedouro;
    }
}