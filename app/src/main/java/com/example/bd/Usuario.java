package com.example.bd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuario", indices = {@Index(value = "nomeUsuario", unique = true)})
public class Usuario {

    @ColumnInfo(name="idUsuario")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int idUsuario;

    @ColumnInfo(name="email")
    @NonNull
    String email;

    @ColumnInfo(name="nomeUsuario")
    @NonNull
    String nomeUsuario;

    @ColumnInfo(name="senha")
    @NonNull
    String senha;

    @Ignore
    public Usuario() {
    }

    public Usuario(String email, String nomeUsuario, String senha) {
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
