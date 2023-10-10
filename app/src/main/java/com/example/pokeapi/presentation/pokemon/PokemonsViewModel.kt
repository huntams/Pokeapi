package com.example.pokeapi.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokeapi.base.BaseViewModel
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.db.model.SpriteDBEntity
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonSpecies
import com.example.pokeapi.data.model.TypePokemon
import com.example.pokeapi.data.remote.model.ApiPokemonType
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.data.remote.model.NamedAPIResource
import com.example.pokeapi.domain.GetPokemonByIdUseCase
import com.example.pokeapi.domain.GetPokemonByNameUseCase
import com.example.pokeapi.domain.GetPokemonEvolutionByIdUseCase
import com.example.pokeapi.domain.GetPokemonSpeciesByIdUseCase
import com.example.pokeapi.domain.GetPokemonsUseCase
import com.example.pokeapi.domain.GetTypeByNameUseCase
import com.example.pokeapi.domain.db.AddPokemonDBUseCase
import com.example.pokeapi.domain.db.AddSpriteDBUseCase
import com.example.pokeapi.domain.db.DeletePokemonDBUseCase
import com.example.pokeapi.domain.db.GetPokemonDBUseCase
import com.example.pokeapi.domain.db.GetPokemonsDBUseCase
import com.example.pokeapi.domain.db.GetPokemonsWithSpritesDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase,
    private val getPokemonSpeciesByIdUseCase: GetPokemonSpeciesByIdUseCase,
    private val getPokemonEvolutionByIdUseCase: GetPokemonEvolutionByIdUseCase,
    private val addPokemonDBUseCase: AddPokemonDBUseCase,
    private val getPokemonDBUseCase: GetPokemonDBUseCase,
    private val deletePokemonDBUseCase: DeletePokemonDBUseCase,
    private val getPokemonsDBUseCase: GetPokemonsDBUseCase,
    private val addSpriteDBUseCase: AddSpriteDBUseCase,
    private val getTypeByNameUseCase: GetTypeByNameUseCase,
) : BaseViewModel() {
    private val _pokemonsLiveData = MutableLiveData<PagingData<NamedAPIResource>>()
    val pokemonsLiveData: LiveData<PagingData<NamedAPIResource>> = _pokemonsLiveData
    private val _pokemonLiveData = MutableLiveData<Pokemon>()
    val pokemonLiveData: LiveData<Pokemon> = _pokemonLiveData
    private val _typeLiveData = MutableLiveData<TypePokemon>()
    val typeLiveData: LiveData<TypePokemon> = _typeLiveData
    private val _pokemonSpeciesLiveData = MutableLiveData<PokemonSpecies>()
    val pokemonSpeciesLiveData: LiveData<PokemonSpecies> = _pokemonSpeciesLiveData
    private val _pokemonEvolutionLiveData = MutableLiveData<EvolutionChain>()
    val pokemonEvolutionLiveData: LiveData<EvolutionChain> = _pokemonEvolutionLiveData
    private val _pokemonsDBLiveData = MutableLiveData<List<PokemonModel>>()
    val pokemonsDBLiveData: LiveData<List<PokemonModel>> = _pokemonsDBLiveData
    private val _pokemonDBLiveData = MutableLiveData<Any?>()
    val pokemonDBLiveData: LiveData<Any?> = _pokemonDBLiveData

    //private val _pokemonsDBLiveData = MutableLiveData<List<PokemonWithSpritesModel>>()
    //val pokemonsDBLiveData: LiveData<List<PokemonWithSpritesModel>> = _pokemonsDBLiveData

    /*
    fun deletePokemonDB(pokemonWithSpritesModel: PokemonWithSpritesModel){
        viewModelScope.launch {
            deletePokemonDBUseCase.execute(pokemonWithSpritesModel)
        }
    }
    fun getPokemonsDB(){
        viewModelScope.launch {
            getPokemonsDBUseCase.execute().collect{list->
                _pokemonsDBLiveData.value = list.map { entity->
                    entity.copy(
                        pokemonEntity = entity.pokemonEntity,
                        sprites = entity.sprites,
                    )
                }

            }
        }
    }

     */
    fun typesToNamedApiResource(types: List<ApiPokemonType>):MutableList<NamedAPIResource>{

        var data:MutableList<NamedAPIResource> = mutableListOf()
        types.map {
            data.add(it.type)
        }
        return data
    }
    fun getTypeByName(name:String){

        viewModelScope.launch {
            getTypeByNameUseCase(name).also {
                _typeLiveData.postValue(it)
            }
        }
    }

    fun addSprite(spriteDBEntity: SpriteDBEntity){
        viewModelScope.launch {
            addSpriteDBUseCase.execute(spriteDBEntity)
        }
    }
    fun deletePokemonDB(pokemonModel: PokemonModel){
        viewModelScope.launch {
            deletePokemonDBUseCase.execute(pokemonModel)
        }
    }
    fun getPokemonsDB(){
        viewModelScope.launch {
            getPokemonsDBUseCase.execute().collect {list->
                _pokemonsDBLiveData.value = list.map{ entity->
                    entity.copy(
                        id = entity.id,
                        name = entity.name,
                        //sprites = entity.sprites,
                        location_area_encounters = entity.location_area_encounters,
                        gender_rate = entity.gender_rate,
                        capture_rate = entity.capture_rate,
                        is_legendary = entity.is_legendary,
                        is_mythical = entity.is_mythical,
                        color = entity.color,
                        //form_descriptions = entity.form_descriptions,
                        //evolution_chain = entity.evolution_chain,
                    )
            }
            }
        }
    }



    fun getPokemonDB(data: Long){
        viewModelScope.launch {
            getPokemonDBUseCase.execute(data).collect { entity->
                _pokemonDBLiveData.value = entity
            }
        }
    }


    fun addPokemonDB(pokemonModel: PokemonModel){
        viewModelScope.launch {
            addPokemonDBUseCase.execute(pokemonModel)
        }
    }

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