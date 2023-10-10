package com.example.pokeapi.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiType(
    @SerialName("damage_relations")
    val damageRelations: ApiTypeRelations,
)