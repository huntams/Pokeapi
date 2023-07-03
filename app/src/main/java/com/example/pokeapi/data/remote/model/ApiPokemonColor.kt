package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiPokemonColor(
    val id: Int,
    val name: String,
)