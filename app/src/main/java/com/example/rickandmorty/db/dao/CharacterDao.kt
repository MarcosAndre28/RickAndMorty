package com.example.rickandmorty.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.db.model.InfoCharacter


@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<InfoCharacter>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<InfoCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(infoCharacters: List<InfoCharacter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(infoCharacter: InfoCharacter)
}