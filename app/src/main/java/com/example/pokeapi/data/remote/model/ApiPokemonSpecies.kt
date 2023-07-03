package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiPokemonSpecies(
    val id: Int,
    val name: String,
    val gender_rate: Int?=null,
    val capture_rate: Int?=null,
    val is_legendary: Boolean?=null,
    val is_mythical: Boolean?=null,
    val color: NamedAPIResource,
    val form_descriptions: List<Description>?=null,
    val evolution_chain: ApiResource,
)
