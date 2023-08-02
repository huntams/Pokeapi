package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.db.model.PokemonWithSprites
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsWithSpritesDBUseCase @Inject constructor(
    private val pokeFavouriteRepository: PokeFavouriteRepository,
) {
    fun execute(): Flow<List<PokemonWithSpritesModel>> {
        return pokeFavouriteRepository.getPokemonsWithSprites()
    }
}