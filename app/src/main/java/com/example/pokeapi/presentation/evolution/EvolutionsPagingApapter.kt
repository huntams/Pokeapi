package com.example.pokeapi.presentation.evolution

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.remote.model.ApiResource
import com.example.pokeapi.databinding.ItemParentEvolutionPokemonBinding
import com.example.pokeapi.presentation.PokemonEvolutionAdapter
import javax.inject.Inject

class EvolutionsPagingApapter @Inject constructor() :
    PagingDataAdapter<ApiResource, EvolutionsPagingApapter.DataViewHolder>(diffUtilCallback) {

    private var onClick: (ApiResource) -> Unit = {}
    fun setCallback(callback: (ApiResource) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemParentEvolutionPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class DataViewHolder(
        private val binding: ItemParentEvolutionPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ApiResource) {


            binding.textViewNumber.text = item.url
            //binding.rvEvolutions.adapter = PokemonEvolutionAdapter(item.url)
            val url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                    item.url
                        .substringAfter("pokemon")
                        .replace("/", "")
                        .padStart(3, '0') +
                    ".png"


        }
    }
}
private val diffUtilCallback = object : DiffUtil.ItemCallback<ApiResource>() {

    override fun areContentsTheSame(oldItem: ApiResource, newItem: ApiResource): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ApiResource, newItem: ApiResource): Boolean {
        return oldItem.url == newItem.url
    }
}