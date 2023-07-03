package com.example.pokeapi.domain

import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.repository.PokeRepository
import javax.inject.Inject

class GetPokemonEvolutionByIdUseCase@Inject constructor(
    private val repository: PokeRepository,
)  {
    suspend operator fun invoke(pokemonId: Int): EvolutionChain {
        return repository.getPokemonEvolutionById(pokemonId)
    }
}