package com.example.pokeapi.data.db.model


import androidx.room.Embedded
import androidx.room.Relation
import org.jetbrains.annotations.NotNull


data class PokemonWithSprites(
    @Embedded
    val pokeEntity: PokeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonEntityId",
    )
    val sprites: List<SpriteDBEntity>
)