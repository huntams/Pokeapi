package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class NamedAPIResource(
    val name: String,
    val url: String,
)
