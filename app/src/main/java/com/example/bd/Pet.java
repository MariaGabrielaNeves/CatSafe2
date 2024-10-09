package com.example.bd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pet")
public class Pet {

    @ColumnInfo(name="idPet")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int idPet;

    @ColumnInfo(name="raça")
    @NonNull
    String raça;

    @ColumnInfo(name="nomePet")
    @NonNull
    String nomePet;

    @ColumnInfo(name="sexo")
    @NonNull
    //Enum
    String sexo;

    @ColumnInfo(name="dataNascimento")
    @NonNull
    String dataNascimento;

    @Ignore
    public Pet(){
    }

    public Pet(int idPet, @NonNull String raça, @NonNull String nomePet, @NonNull String sexo, @NonNull String dataNascimento) {
        this.idPet = 0;
        this.raça = raça;
        this.nomePet = nomePet;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }
}
