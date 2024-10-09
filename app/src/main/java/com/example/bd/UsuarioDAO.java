package com.example.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsuarioDAO {

    @Insert
    public void addUsuario(Usuario usuario);

    @Update
    public void updateUsuario(Usuario usuario);

    @Delete
    public void deleteUsuario(Usuario usuario);

    @Query("select count(*) from Usuario where nomeUsuario = :nomeUsuario and email = :email and senha = :senha")
    public boolean isInserted(String nomeUsuario, String email, String senha);


    @Query("select count(*) from Usuario where nomeUsuario = :nomeUsuario and senha = :senha")
    public int checkUser(String nomeUsuario, String senha);

    @Query("select email from Usuario where nomeUsuario = :nomeUsuario")
    public String getUserEmail(String nomeUsuario);


}
