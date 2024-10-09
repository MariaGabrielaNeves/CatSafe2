package com.example.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PetDAO {

    @Insert
    public void addPet(Pet pet);

    @Update
    public void updatePet(Pet pet);

    @Delete
    public void deletePet(Pet pet);

    @Query("select * from Pet where idPet==:idPet")
    public Pet getPet(int idPet);
}
