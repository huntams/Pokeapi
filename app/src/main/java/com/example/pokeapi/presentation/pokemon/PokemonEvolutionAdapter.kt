package com.example.pokeapi.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.databinding.ItemEvolutionPokemonBinding
import javax.inject.Inject

class PokemonEvolutionAdapter @Inject constructor() : ListAdapter<ChainLink, PokemonEvolutionAdapter.DataViewHolder>(
    diffUtilCallback
) {

    private var onClick: (ChainLink) -> Unit = {}
    fun setCallback(callback: (ChainLink) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemEvolutionPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemEvolutionPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChainLink) {

            with(binding) {
                textViewName.text = item.species.name
                textViewNumber.text = item.species.url
                    .substringAfter("pokemon-species")
                    .replace("/", "")
                    .padStart(3, '0')
                imageViewRounded.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ item.species.url
                    .substringAfter("pokemon-species")
                    .replace("/", "")+".png")
                //closeAddImageView.setImageURI()

                root.setOnClickListener {
                    onClick.invoke(item)
                }


            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<ChainLink>() {

    override fun areContentsTheSame(oldItem: ChainLink, newItem: ChainLink): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ChainLink, newItem: ChainLink): Boolean {
        return oldItem.species.name == newItem.species.name
    }
}