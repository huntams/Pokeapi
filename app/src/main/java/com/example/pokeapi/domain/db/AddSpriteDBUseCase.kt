package com.example.pokeapi.domain.db

import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.model.SpriteDBEntity
import com.example.pokeapi.data.remote.repository.PokeFavouriteRepository
import javax.inject.Inject

class AddSpriteDBUseCase @Inject constructor(
    private val pokeFavouriteRepository: PokeFavouriteRepository,
) {
    suspend fun execute(spriteDBEntity: SpriteDBEntity) {
        pokeFavouriteRepository.addSprites(spriteDBEntity)
    }
}