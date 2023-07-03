package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class EvolutionChain(
    val id: Int,
    val baby_trigger_item: NamedAPIResource? = null,
    val chain: ChainLink,
)