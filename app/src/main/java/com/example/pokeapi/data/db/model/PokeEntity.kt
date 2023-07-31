package com.example.pokeapi.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemons")
data class PokeEntity(
    @PrimaryKey val id: Long,
    val name: String?=null,
    val location_area_encounters: String?=null,
    val gender_rate: Int?=null,
    val capture_rate: Int?=null,
    val is_legendary: Boolean?=null,
    val is_mythical: Boolean?=null,
    val color: String?=null,
    //val form_descriptions: List<Description>?=null,
    //val evolution_chain: ApiResource,
)