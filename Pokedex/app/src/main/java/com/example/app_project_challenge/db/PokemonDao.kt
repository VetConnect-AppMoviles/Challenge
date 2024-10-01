package com.example.app_project_challenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app_project_challenge.models.Pokemon

@Dao
interface PokemonDao {
    @Insert
    fun insertOne(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Delete
    fun delete(pokemon: Pokemon)
}