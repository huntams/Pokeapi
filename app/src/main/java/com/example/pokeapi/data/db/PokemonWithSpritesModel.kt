package com.example.pokeapi.data.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokeapi.data.db.model.PokeEntity
import com.example.pokeapi.data.db.model.SpriteDBEntity

data class PokemonWithSpritesModel(
    val pokemonEntity: PokeEntity,
    val sprites: List<SpriteDBEntity>
)