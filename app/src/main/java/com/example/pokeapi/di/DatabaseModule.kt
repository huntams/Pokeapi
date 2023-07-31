package com.example.pokeapi.di

import android.content.Context
import androidx.room.Room
import com.example.pokeapi.data.db.PokeDAO
import com.example.pokeapi.data.db.PokeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "PokemonsDatabase"

    @Provides
    @Singleton
    fun providePokemonsDatabase(
        @ApplicationContext context: Context,
    ): PokeDB {
        return Room.databaseBuilder(
            context,
            PokeDB::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDAO(db: PokeDB): PokeDAO  {
        return db.pokesDAO()
    }
}