package com.example.pokeapi.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokeapi.data.mappers.PokemonMapper
import com.example.pokeapi.data.mappers.PokemonSpeciesMapper
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.pagging.EvolutionsPagingSource
import com.example.pokeapi.data.pagging.PokemonPagingSource
import com.example.pokeapi.data.remote.PokeApiService
import com.example.pokeapi.data.remote.model.ApiPokemonColor
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.model.NamedAPIResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService,
    private val pokemonMapper: PokemonMapper,
    private val pokemonSpeciesMapper: PokemonSpeciesMapper,
) : PokeRepository{
    override suspend fun getPokemons(): Flow<PagingData<NamedAPIResource>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { PokemonPagingSource(apiService) },
        ).flow.map {pagingdata->
            pagingdata.map {
                it
            }

        }
    }

    override suspend fun getEvolutions(): Flow<PagingData<ApiResource>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { EvolutionsPagingSource(apiService) },
        ).flow.map {pagingdata->
            pagingdata.map {
                it
            }

        }
    }


    override suspend fun getPokemonById(pokemonId: Int): Pokemon {
        return pokemonMapper.apiToModel(apiService.getPokemonById(pokemonId))
    }

    override suspend fun getPokemonByName(Name: String): Pokemon {
        return pokemonMapper.apiToModel(apiService.getPokemonByName(Name))
    }

    override suspend fun getPokemonSpeciesById(pokemonId: Int): PokemonSpecies {
        return pokemonSpeciesMapper.apiToModel(apiService.getPokemonSpeciesById(pokemonId))
    }

    override suspend fun getPokemonColorById(pokemonId: Int): ApiPokemonColor {
        return apiService.getPokemonColorById(pokemonId)
    }

    override suspend fun getPokemonEvolutionById(pokemonId: Int): EvolutionChain {
        return apiService.getPokemonEvolutionById(pokemonId)
    }
}