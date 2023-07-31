package com.example.pokeapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokeapi.data.db.model.PokeEntity

@Database(entities = [PokeEntity::class,], version = 1)
abstract class PokeDB : RoomDatabase() {
    abstract fun pokesDAO(): PokeDAO
}