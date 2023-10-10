package com.example.pokeapi.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.base.BaseViewModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.domain.db.GetPokemonWithSpritesUseCase
import com.example.pokeapi.domain.db.GetPokemonsWithSpritesDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritePokemonViewModel @Inject constructor(
    private val getPokemonsWithSpritesDBUseCase: GetPokemonsWithSpritesDBUseCase,
    private val getPokemonWithSpritesUseCase: GetPokemonWithSpritesUseCase,
) : BaseViewModel() {

    private val _pokemonsSpritesDBLiveData = MutableLiveData<List<PokemonWithSpritesModel>>()
    val pokemonsSpritesDBLiveData: LiveData<List<PokemonWithSpritesModel>> =
        _pokemonsSpritesDBLiveData

    private val _pokemonSpritesDBLiveData = MutableLiveData<PokemonWithSpritesModel>()
    val pokemonSpritesDBLiveData: LiveData<PokemonWithSpritesModel> = _pokemonSpritesDBLiveData

    fun getPokemonsWithSprites() {
        viewModelScope.launch {
            getPokemonsWithSpritesDBUseCase.execute().collect { list ->
                _pokemonsSpritesDBLiveData.value = list.map { entity ->
                    entity.copy(
                        pokemonEntity = entity.pokemonEntity,
                        sprites = entity.sprites

                    )
                }
            }
        }
    }

    fun getPokemonWithSprites(pokemonId: Long){
        viewModelScope.launch {
            getPokemonWithSpritesUseCase(pokemonId).collect { entity ->
                _pokemonSpritesDBLiveData.value = entity
            }
        }
    }
}