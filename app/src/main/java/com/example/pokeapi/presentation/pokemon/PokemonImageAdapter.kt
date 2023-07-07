package com.example.pokeapi.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.model.PokemonImages
import com.example.pokeapi.databinding.ItemImagePokemonBinding
import javax.inject.Inject

class PokemonImageAdapter @Inject constructor() : ListAdapter<PokemonImages, PokemonImageAdapter.DataViewHolder>(
    diffUtilCallback
) {

    private var onClick: (PokemonImages) -> Unit = {}
    fun setCallback(callback: (PokemonImages) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemImagePokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemImagePokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonImages) {

            with(binding) {
                image.load(item.url)
                //closeAddImageView.setImageURI()

                root.setOnClickListener {
                    onClick.invoke(item)
                }


            }

        }
    }
}
private val diffUtilCallback = object : DiffUtil.ItemCallback<PokemonImages>() {

    override fun areContentsTheSame(oldItem: PokemonImages, newItem: PokemonImages): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: PokemonImages, newItem: PokemonImages): Boolean {
        return oldItem.id == newItem.id
    }
}