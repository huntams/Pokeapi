package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsDBUseCase @Inject constructor(
    private val pokeFavouriteRepository: PokeFavouriteRepository,
) {
    fun execute(): Flow<List<PokemonModel>> {
        return pokeFavouriteRepository.getPokemons()
    }
}