package com.example.pokeapi.data.remote.repository

import com.example.pokeapi.data.db.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokeFavouriteRepository {
    fun getPokemons(): Flow<List<PokemonModel>>

    fun filterPokemons(data: String): Flow<List<PokemonModel>>

    suspend fun addPokemon(pokemonModel: PokemonModel)

    suspend fun deletePokemon(pokemonModel: PokemonModel)

}