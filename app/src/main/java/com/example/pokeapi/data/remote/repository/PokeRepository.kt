package com.example.pokeapi.data.remote.repository

import androidx.paging.PagingData
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.remote.model.ApiPokemon
import com.example.pokeapi.data.remote.model.ApiPokemonColor
import com.example.pokeapi.data.remote.model.ApiPokemonSpecies
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.model.NamedAPIResource
import kotlinx.coroutines.flow.Flow

interface PokeRepository {

    suspend fun getPokemonById(pokemonId: Int): Pokemon

    suspend fun getPokemonByName(Name:String): Pokemon

    suspend fun getPokemons(): Flow<PagingData<NamedAPIResource>>

    suspend fun getPokemonSpeciesById(pokemonId: Int): PokemonSpecies

    suspend fun getPokemonColorById(pokemonId: Int): ApiPokemonColor
    suspend fun getPokemonEvolutionById(pokemonId: Int): EvolutionChain
}