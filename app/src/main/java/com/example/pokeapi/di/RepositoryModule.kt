package com.example.pokeapi.di

import com.example.pokeapi.data.remote.repository.PokeRepository
import com.example.pokeapi.data.remote.repository.PokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule() {

    @Binds
    abstract fun bindPokeRepository(impl: PokeRepositoryImpl) : PokeRepository
}