package com.example.pokeapi.data.mappers

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.model.PokeEntity
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.remote.model.ApiPokemon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonMapper @Inject constructor() {

    fun fromEntityToUIModel(entity: PokeEntity): PokemonModel {
        return PokemonModel(
            id = entity.id,
            name = entity.name,
            //sprites = entity.sprites,
            location_area_encounters = entity.location_area_encounters,
            gender_rate = entity.gender_rate,
            capture_rate = entity.capture_rate,
            is_legendary = entity.is_legendary,
            is_mythical = entity.is_mythical,
            color = entity.color,
            //form_descriptions = entity.form_descriptions,
            //evolution_chain = entity.evolution_chain,
        )
    }
    fun fromUIModelToEntity(pokemonModel: PokemonModel): PokeEntity {
        return PokeEntity(
            id = pokemonModel.id,
            name = pokemonModel.name,
            //sprites = pokemonModel.sprites,
            location_area_encounters = pokemonModel.location_area_encounters,
            gender_rate = pokemonModel.gender_rate,
            capture_rate = pokemonModel.capture_rate,
            is_legendary = pokemonModel.is_legendary,
            is_mythical = pokemonModel.is_mythical,
            color = pokemonModel.color,
            //form_descriptions = pokemonModel.form_descriptions,
            //evolution_chain = pokemonModel.evolution_chain,
        )
    }

    fun apiToModel(apiPokemon: ApiPokemon) = Pokemon(
        id = apiPokemon.id,
        name = apiPokemon.name,
        sprites = apiPokemon.sprites,
        location_area_encounters = apiPokemon.location_area_encounters,
    )
}