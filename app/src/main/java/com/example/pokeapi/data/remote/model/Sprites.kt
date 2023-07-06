package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    val front_default: String?=null,
    val front_shiny: String?=null,
    val front_female: String?=null,
    val front_shiny_female: String?=null,
    val back_default: String?=null,
    val back_shiny: String?=null,
    val back_female: String?=null,
    val back_shiny_female: String?=null
)