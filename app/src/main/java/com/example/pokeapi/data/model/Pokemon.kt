package com.example.pokeapi.data.model

import com.example.pokeapi.data.remote.model.ApiPokemonType
import com.example.pokeapi.data.remote.model.Sprites

data class Pokemon(
    val id: Int,
    val name: String,
    val sprites : Sprites,
    val location_area_encounters: String?=null,
    val height: Int,
    val weight: Int,
    val types: List<ApiPokemonType>
)