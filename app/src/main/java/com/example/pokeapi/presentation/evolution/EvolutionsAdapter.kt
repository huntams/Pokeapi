package com.example.pokeapi.presentation.pokemon

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.databinding.ItemParentEvolutionPokemonBinding
import javax.inject.Inject

class EvolutionsAdapter @Inject constructor() : ListAdapter<EvolutionChain, EvolutionsAdapter.DataViewHolder>(
    diffUtilCallback
) {

    private var onClick: (EvolutionChain) -> Unit = {}
    fun setCallback(callback: (EvolutionChain) -> Unit) {
        this.onClick = callback
    }
    private var chainLinks: MutableList<ChainLink?> = mutableListOf()

    private fun pokemonChain(chainLink: List<ChainLink>) {
        if (chainLink[0].evolves_to.isNotEmpty()) {
            Log.i(chainLink[0].species.name, chainLink[0].evolves_to.size.toString())
            chainLinks.add(chainLink[0])
            pokemonChain(chainLink[0].evolves_to)//not null
        } else
        //null
            chainLinks.add(chainLink[0])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemParentEvolutionPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemParentEvolutionPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EvolutionChain) {
            pokemonChain(listOf(item.chain))
            with(binding) {
                textViewNumber.text = item.id.toString()
                val evolutionAdapter = PokemonEvolutionAdapter()
                evolutionAdapter.submitList(chainLinks)
                rvEvolutions.adapter = evolutionAdapter
                root.setOnClickListener {
                    onClick.invoke(item)
                }


            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<EvolutionChain>() {

    override fun areContentsTheSame(oldItem: EvolutionChain, newItem: EvolutionChain): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: EvolutionChain, newItem: EvolutionChain): Boolean {
        return oldItem.id == newItem.id
    }
}