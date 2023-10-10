package com.example.pokeapi.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTypeRelations(
    @SerialName("double_damage_to")
    val doubleDamageTo: List<NamedAPIResource>,
    @SerialName("double_damage_from")
    val doubleDamageFrom: List<NamedAPIResource>,
)