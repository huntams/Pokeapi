package com.example.pokeapi.data.remote.repository

import com.example.pokeapi.data.db.PokeDAO
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.mappers.PokemonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokeFavouriteRepositoryImpl @Inject constructor(
    private val pokemonMapper: PokemonMapper,
    private val notesDAO: PokeDAO,
) : PokeFavouriteRepository {
    override fun getPokemons(): Flow<List<PokemonModel>> {
        return notesDAO.getPokemons().map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override fun filterPokemons(data: String): Flow<List<PokemonModel>> {
        return notesDAO.filterPokemons(data).map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override suspend fun addPokemon(pokemonModel: PokemonModel) {
        notesDAO.addPokemon(pokemonMapper.fromUIModelToEntity(pokemonModel))
    }

    override suspend fun deletePokemon(pokemonModel: PokemonModel) {
        notesDAO.deletePokemon(pokemonMapper.fromUIModelToEntity(pokemonModel))
    }
}