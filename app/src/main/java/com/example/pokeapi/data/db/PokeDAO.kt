package com.example.pokeapi.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokeapi.data.db.model.PokeEntity
import com.example.pokeapi.data.db.model.PokemonWithSprites
import com.example.pokeapi.data.db.model.SpriteDBEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDAO {
    @Insert
    suspend fun addPokemon(pokeEntity: PokeEntity): Long

    @Transaction
    @Query("SELECT * FROM Pokemons")
    fun getPokemonsWithSprites(): Flow<List<PokemonWithSprites>>

    @Transaction
    @Query("SELECT * FROM Pokemons WHERE poke_id LIKE :data")
    fun getPokemonWithSprites(data: Long): Flow<PokemonWithSprites>

    @Insert
    suspend fun addSprites(spriteDBEntity: SpriteDBEntity)

    @Query("SELECT * FROM Pokemons WHERE poke_id LIKE :data")
    fun getPokemon(data: Long): Flow<PokeEntity?>

    @Query("SELECT * FROM Pokemons")
    fun getPokemons(): Flow<List<PokeEntity>>

    @Query("SELECT * FROM Pokemons WHERE name LIKE :data || '%'")
    fun filterPokemons(data: String): Flow<List<PokeEntity>>

    @Delete
    suspend fun deletePokemon(pokeEntity: PokeEntity)
}