package com.example.pokeapi.data.mappers

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.remote.model.ApiPokemon
import com.example.pokeapi.data.remote.model.ApiPokemonSpecies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonSpeciesMapper @Inject constructor() {

    fun apiToModel(apiPokemonSpecies: ApiPokemonSpecies) = PokemonSpecies(
        id = apiPokemonSpecies.id,
        name = apiPokemonSpecies.name,
        gender_rate = apiPokemonSpecies.gender_rate,
        capture_rate = apiPokemonSpecies.capture_rate,
        is_legendary = apiPokemonSpecies.is_legendary,
        is_mythical = apiPokemonSpecies.is_mythical,
        color = apiPokemonSpecies.color,
        form_descriptions = apiPokemonSpecies.form_descriptions,
        evolution_chain = apiPokemonSpecies.evolution_chain,
    )
}