package com.example.pokeapi.domain

import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.remote.repository.PokeRepository
import javax.inject.Inject

class GetPokemonSpeciesByIdUseCase@Inject constructor(
    private val repository: PokeRepository,
)  {
    suspend operator fun invoke(pokemonId: Int): PokemonSpecies {
        return repository.getPokemonSpeciesById(pokemonId)
    }
}