package com.example.pokeapi.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class PageDataResponse<T>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>
)