package com.example.pokeapi.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemons")
data class PokeEntity(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    //val sprites : ByteArray,
    val location_area_encounters: String?=null,
    val gender_rate: Int?=null,
    val capture_rate: Int?=null,
    val is_legendary: Boolean?=null,
    val is_mythical: Boolean?=null,
    val color: String,
    //val form_descriptions: List<Description>?=null,
    //val evolution_chain: ApiResource,
)