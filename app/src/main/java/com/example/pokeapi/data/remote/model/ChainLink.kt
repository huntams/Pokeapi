package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class ChainLink(
    val is_baby: Boolean?=null,
    val species: NamedAPIResource,
    val evolution_details:List<EvolutionDetail>?=null,
    val evolves_to: List<ChainLink>,
)