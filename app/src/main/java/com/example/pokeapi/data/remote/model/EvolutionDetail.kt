package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class EvolutionDetail(
    val min_level: Int?=null,
    val trigger: NamedAPIResource?=null,
)