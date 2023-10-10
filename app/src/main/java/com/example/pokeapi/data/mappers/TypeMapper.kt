package com.example.pokeapi.data.mappers

import com.example.pokeapi.data.model.TypePokemon
import com.example.pokeapi.data.remote.model.ApiType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypeMapper @Inject constructor() {
    fun fromModelToUi(apiType: ApiType):TypePokemon{
        return TypePokemon(
            damageRelations = apiType.damageRelations
        )
    }
}