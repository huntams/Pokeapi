package com.example.pokeapi.data.mappers

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.remote.model.ApiPokemon
import com.example.pokeapi.data.remote.model.Sprites
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonMapper @Inject constructor() {

    fun apiToModel(apiPokemon: ApiPokemon) = Pokemon(
        id = apiPokemon.id,
        name = apiPokemon.name,
        sprites = apiPokemon.sprites,
        location_area_encounters = apiPokemon.location_area_encounters,
        )
}