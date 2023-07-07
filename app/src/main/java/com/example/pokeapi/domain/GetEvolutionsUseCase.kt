package com.example.pokeapi.domain

import androidx.paging.PagingData
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.data.remote.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEvolutionsUseCase @Inject constructor(
    private val repository: PokeRepository,
) {

    suspend fun execute() : Flow<PagingData<ApiResource>> {
        return repository.getEvolutions()
    }
}