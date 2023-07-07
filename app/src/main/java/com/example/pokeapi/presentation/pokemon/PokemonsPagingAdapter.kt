package com.example.pokeapi.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.remote.model.NamedAPIResource
import com.example.pokeapi.databinding.ItemPokemonBinding
import javax.inject.Inject

class PokemonsPagingAdapter @Inject constructor() :
    PagingDataAdapter<NamedAPIResource, PokemonsPagingAdapter.DataViewHolder>(diffUtilCallback) {

    private var onClick: (NamedAPIResource) -> Unit = {}
    fun setCallback(callback: (NamedAPIResource) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class DataViewHolder(
        private val binding: ItemPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NamedAPIResource) {

            val url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                    item.url
                        .substringAfter("pokemon")
                        .replace("/", "")
                        .padStart(3, '0') +
                    ".png"


            with(binding) {
                imagepart.load(url)

                number.text = item.name

                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<NamedAPIResource>() {

    override fun areContentsTheSame(oldItem: NamedAPIResource, newItem: NamedAPIResource): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: NamedAPIResource, newItem: NamedAPIResource): Boolean {
        return oldItem.name == newItem.name
    }
}