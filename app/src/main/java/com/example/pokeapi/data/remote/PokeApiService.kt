package com.example.pokeapi.data.remote

import com.example.pokeapi.data.remote.model.ApiPokemon
import com.example.pokeapi.data.remote.model.ApiPokemonColor
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.data.remote.model.PageDataResponse
import com.example.pokeapi.data.remote.model.ApiPokemonSpecies
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.model.NamedAPIResource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: String?,
    ): PageDataResponse<NamedAPIResource>

    @GET("evolution-chain/")
    suspend fun getEvolutions(
        @Query("limit") limit: Int,
        @Query("offset") offset: String?,
    ): PageDataResponse<ApiResource>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): ApiPokemon

    @GET("pokemon/{Name}")
    suspend fun getPokemonByName(
        @Path("Name") Name: String
    ): ApiPokemon

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonSpeciesById(
        @Path("pokemonId") pokemonId: Int
    ): ApiPokemonSpecies

    @GET("pokemon-color/{pokemonId}")
    suspend fun getPokemonColorById(
        @Path("pokemonId") pokemonId: Int
    ): ApiPokemonColor

    @GET("evolution-chain/{pokemonId}")
    suspend fun getPokemonEvolutionById(
        @Path("pokemonId") pokemonId: Int
    ): EvolutionChain
}