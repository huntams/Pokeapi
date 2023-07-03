package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiPokemon(
    val id: Int,
    val name: String,
    val sprites : Sprites,
    val location_area_encounters: String?=null,
    )