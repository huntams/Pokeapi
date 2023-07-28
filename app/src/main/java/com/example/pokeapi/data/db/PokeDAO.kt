package com.example.pokeapi.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokeapi.data.db.model.PokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDAO {
    @Insert
    suspend fun addPokemon(note: PokeEntity): Long

    @Query("SELECT * FROM Pokemons")
    fun getPokemons(): Flow<List<PokeEntity>>

    @Query("SELECT * FROM Pokemons WHERE name LIKE :data || '%'")
    fun filterPokemons(data: String): Flow<List<PokeEntity>>

    @Delete
    suspend fun deletePokemon(note: PokeEntity)
}