package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import javax.inject.Inject

class AddPokemonDBUseCase @Inject constructor(
    private val pokeFavouriteRepository: PokeFavouriteRepository,
) {
    suspend fun execute(pokemonModel: PokemonModel) {
        pokeFavouriteRepository.addPokemon(pokemonModel)
    }
}