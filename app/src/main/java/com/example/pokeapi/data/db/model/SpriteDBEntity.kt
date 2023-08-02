package com.example.pokeapi.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "sprites"
)
data class SpriteDBEntity(
    @PrimaryKey(autoGenerate = true) val spriteId: Long = 0,
    @ColumnInfo(name = "pokeEntityId")
    val pokemonEntityId: Long,
    val sprite: ByteArray,
)