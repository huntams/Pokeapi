package com.example.pokeapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokeapi.base.BaseViewModel
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.remote.ResultLoader
import com.example.pokeapi.data.remote.model.ApiPokemonColor
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.model.NamedAPIResource
import com.example.pokeapi.domain.GetPokemonByIdUseCase
import com.example.pokeapi.domain.GetPokemonByNameUseCase
import com.example.pokeapi.domain.GetPokemonColorByIdUseCase
import com.example.pokeapi.domain.GetPokemonEvolutionByIdUseCase
import com.example.pokeapi.domain.GetPokemonSpeciesByIdUseCase
import com.example.pokeapi.domain.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase,
    private val getPokemonSpeciesByIdUseCase: GetPokemonSpeciesByIdUseCase,
    private val getPokemonEvolutionByIdUseCase: GetPokemonEvolutionByIdUseCase,

) : BaseViewModel() {
    private val _pokemonsLiveData = MutableLiveData<PagingData<NamedAPIResource>>()
    val pokemonsLiveData: LiveData<PagingData<NamedAPIResource>> = _pokemonsLiveData
    private val _pokemonLiveData = MutableLiveData<Pokemon>()
    val pokemonLiveData: LiveData<Pokemon> = _pokemonLiveData
    private val _pokemonSpeciesLiveData = MutableLiveData<PokemonSpecies>()
    val pokemonSpeciesLiveData: LiveData<PokemonSpecies> = _pokemonSpeciesLiveData
    private val _pokemonEvolutionLiveData = MutableLiveData<EvolutionChain>()
    val pokemonEvolutionLiveData: LiveData<EvolutionChain> = _pokemonEvolutionLiveData
    /*
    private val _pokemonsLiveData = MutableLiveData<ResultLoader<PagingData<NamedAPIResource>>>()
    val pokemonsLiveData: LiveData<ResultLoader<PagingData<NamedAPIResource>>> = _pokemonsLiveData
    private val _pokemonLiveData = MutableLiveData<ResultLoader<Pokemon>>()
    val pokemonLiveData: LiveData<ResultLoader<Pokemon>> = _pokemonLiveData

     */


    fun getPokemons() {
        /*
        _pokemonsLiveData.loadDataPaging {
            getPokemonsUseCase.execute().cachedIn(viewModelScope)
        }

         */

        viewModelScope.launch {
            getPokemonsUseCase.execute().cachedIn(this).also { flow ->
                flow.collect {
                    _pokemonsLiveData.postValue(it)
                }
            }
        }


    }


    fun getPokemonById(pokemonId: Int) {
        /*
        _pokemonLiveData.loadData {
            getPokemonByIdUseCase(pokemonId)
        }

         */

        viewModelScope.launch {
            getPokemonByIdUseCase(pokemonId).also {
                _pokemonLiveData.postValue(it)
            }
        }


    }

    fun getPokemonByName(pokemonName: String) {
        /*
        _pokemonLiveData.loadData {
            getPokemonByNameUseCase(pokemonName)
        }

         */

        viewModelScope.launch {
            getPokemonByNameUseCase(pokemonName).also {
                _pokemonLiveData.postValue(it)
            }
        }
    }

    fun getPokemonSpeciesById(pokemonId: Int) {
        viewModelScope.launch {
            getPokemonSpeciesByIdUseCase(pokemonId).also {
                _pokemonSpeciesLiveData.postValue(it)
            }
        }
    }

    fun getPokemonEvolutionById (pokemonId: Int) {
        viewModelScope.launch {
            getPokemonEvolutionByIdUseCase(pokemonId).also {
                _pokemonEvolutionLiveData.postValue(it)
            }
        }
    }
}