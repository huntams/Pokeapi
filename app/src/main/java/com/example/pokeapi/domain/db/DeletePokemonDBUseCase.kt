package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import javax.inject.Inject

class DeletePokemonDBUseCase @Inject constructor(
    private val pokemonFavouriteRepository: PokeFavouriteRepository,
) {
    suspend fun execute(pokemonModel: PokemonModel) {
        pokemonFavouriteRepository.deletePokemon(pokemonModel)
    }
}