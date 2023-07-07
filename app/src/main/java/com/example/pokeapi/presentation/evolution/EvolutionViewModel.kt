package com.example.pokeapi.presentation.evolution

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokeapi.base.BaseViewModel
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.domain.GetPokemonEvolutionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EvolutionViewModel @Inject constructor(
    private val getPokemonEvolutionByIdUseCase: GetPokemonEvolutionByIdUseCase
) : BaseViewModel() {

    private val _pokemonEvolutionLiveData = MutableLiveData<EvolutionChain>()
    val pokemonEvolutionLiveData: LiveData<EvolutionChain> = _pokemonEvolutionLiveData

    fun getPokemonEvolutionById (pokemonId: Int) {
        viewModelScope.launch {
            getPokemonEvolutionByIdUseCase(pokemonId).also {
                _pokemonEvolutionLiveData.postValue(it)
            }
        }
    }
}