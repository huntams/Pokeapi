package com.example.pokeapi.data.remote.repository

import com.example.pokeapi.data.db.PokeDAO
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.db.model.PokeEntity
import com.example.pokeapi.data.db.model.PokemonWithSprites
import com.example.pokeapi.data.db.model.SpriteDBEntity
import com.example.pokeapi.data.mappers.PokemonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokeFavouriteRepositoryImpl @Inject constructor(
    private val pokemonMapper: PokemonMapper,
    private val pokeDAO: PokeDAO,
) : PokeFavouriteRepository {
    /*
    override suspend fun addPokemon(pokemonWithSpritesModel: PokemonWithSpritesModel) {
        pokeDAO.addPokemon(pokemonMapper.fromUIModelToEntity(pokemonWithSpritesModel))
    }

    override suspend fun deletePokemon(pokemonWithSpritesModel: PokemonWithSpritesModel) {
        pokeDAO.addPokemon(pokemonMapper.fromUIModelToEntity(pokemonWithSpritesModel))
    }

    override fun getPokemon(data: Long): Flow<Any?> {
        return pokeDAO.getPokemon(data).map { entity ->
            entity ?: entity?.also {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override fun getPokemons(): Flow<List<PokemonWithSpritesModel>> {
        return pokeDAO.getPokemons().map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override fun filterPokemons(data: String): Flow<List<PokemonWithSpritesModel>> {
        return pokeDAO.filterPokemons(data).map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

     */
    override suspend fun addSprites(spriteDBEntity: SpriteDBEntity) {
        pokeDAO.addSprites(spriteDBEntity)
    }

    override fun getPokemonsWithSprites(): Flow<List<PokemonWithSpritesModel>> {
        return pokeDAO.getPokemonsWithSprites().map { list ->
            list.map {
                pokemonMapper.fromSpritesEntityToUIModel(it)
            }
        }
    }

    override fun getPokemonWithSprites(data: Long): Flow<PokemonWithSpritesModel> {
        return pokeDAO.getPokemonWithSprites(data).map { entity->
                pokemonMapper.fromSpritesEntityToUIModel(entity)
        }
    }

    override fun getPokemon(data: Long): Flow<Any?> {
        return pokeDAO.getPokemon(data).map { entity ->
            entity ?: entity?.also {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override fun getPokemons(): Flow<List<PokemonModel>> {
        return pokeDAO.getPokemons().map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override fun filterPokemons(data: String): Flow<List<PokemonModel>> {
        return pokeDAO.filterPokemons(data).map { list ->
            list.map {
                pokemonMapper.fromEntityToUIModel(it)
            }
        }
    }

    override suspend fun addPokemon(pokemonModel: PokemonModel) {
        pokeDAO.addPokemon(pokemonMapper.fromUIModelToEntity(pokemonModel))
    }

    override suspend fun deletePokemon(pokemonModel: PokemonModel) {
        pokeDAO.deletePokemon(pokemonMapper.fromUIModelToEntity(pokemonModel))
    }


}