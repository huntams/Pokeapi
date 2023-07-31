package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDBUseCase @Inject constructor(
    private val pokeFavouriteRepository: PokeFavouriteRepository,
) {
    fun execute(data: Long): Flow<Any?> {
        return pokeFavouriteRepository.getPokemon(data)
    }
}