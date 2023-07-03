package com.example.pokeapi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.remote.model.Sprites
import com.example.pokeapi.databinding.ItemImagePokemonBinding
import com.example.pokeapi.databinding.ItemPokemonBinding
import javax.inject.Inject

class PokemonImageAdapter @Inject constructor() : ListAdapter<Sprites, PokemonImageAdapter.DataViewHolder>(diffUtilCallback) {

    private var onClick: (Sprites) -> Unit = {}
    fun setCallback(callback: (Sprites) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemImagePokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemImagePokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Sprites) {

            with(binding) {

                image.load(item.front_default)
                //closeAddImageView.setImageURI()

                root.setOnClickListener {
                    onClick.invoke(item)
                }


            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<Sprites>() {

    override fun areContentsTheSame(oldItem: Sprites, newItem: Sprites): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Sprites, newItem: Sprites): Boolean {
        return oldItem.back_default == newItem.back_default
    }
}