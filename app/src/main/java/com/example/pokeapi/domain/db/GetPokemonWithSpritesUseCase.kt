package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonWithSpritesUseCase@Inject constructor(
    private val repository: PokeFavouriteRepository,
)  {
    suspend operator fun invoke(pokemonId: Long): Flow<PokemonWithSpritesModel> {
        return repository.getPokemonWithSprites(pokemonId)
    }
}