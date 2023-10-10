package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiPokemonType(
    val slot: Int,
    val type: NamedAPIResource,
)