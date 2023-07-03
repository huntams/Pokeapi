package com.example.pokeapi.domain

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.remote.repository.PokeRepository
import javax.inject.Inject

class GetPokemonByNameUseCase@Inject constructor(
    private val repository: PokeRepository,
)  {
    suspend operator fun invoke(pokemonName:String): Pokemon {
        return repository.getPokemonByName(pokemonName)
    }
}