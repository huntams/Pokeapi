package com.example.pokeapi.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeapi.data.remote.PokeApiService
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.data.remote.model.NamedAPIResource

class PokemonPagingSource(
    private val apiService: PokeApiService,
) : PagingSource<String, NamedAPIResource>() {
    override fun getRefreshKey(state: PagingState<String, NamedAPIResource>): String? {
        return null

    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, NamedAPIResource> {
        try {
            val response = apiService.getPokemons(
                limit = params.loadSize,
                offset = params.key
            )
            val next = response.next?.indexOf("offset=", 0)
            val end = next?.let { response.next.indexOf("&limit", it) }
            return LoadResult.Page(
                data = response.results,
                nextKey = end?.let { response.next.substring(next+7, it) },
                prevKey = response.previous
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}