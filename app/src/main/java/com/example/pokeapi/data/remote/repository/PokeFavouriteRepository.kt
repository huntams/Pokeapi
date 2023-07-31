package com.example.pokeapi.data.remote.repository

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.db.model.PokeEntity
import com.example.pokeapi.data.db.model.PokemonWithSprites
import kotlinx.coroutines.flow.Flow

interface PokeFavouriteRepository {
    fun getPokemons(): Flow<List<PokemonModel>>
    fun getPokemon(data: Long): Flow<Any?>

    fun filterPokemons(data: String): Flow<List<PokemonModel>>

    suspend fun addPokemon(pokemonModel: PokemonModel)

    suspend fun deletePokemon(pokemonModel: PokemonModel)
    /*
    suspend fun addPokemon(pokemonWithSpritesModel: PokemonWithSpritesModel)

    fun getPokemon(data: Long): Flow<Any?>

    fun getPokemons(): Flow<List<PokemonWithSpritesModel>>

    fun filterPokemons(data: String): Flow<List<PokemonWithSpritesModel>>

    suspend fun deletePokemon(pokemonWithSpritesModel: PokemonWithSpritesModel)

     */
}