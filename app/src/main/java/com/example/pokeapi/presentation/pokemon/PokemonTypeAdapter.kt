package com.example.pokeapi.presentation.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.base.nameToId
import com.example.pokeapi.data.remote.model.ApiPokemonType
import com.example.pokeapi.data.remote.model.NamedAPIResource
import com.example.pokeapi.databinding.ItemTypePokemonBinding
import javax.inject.Inject

class PokemonTypeAdapter @Inject constructor() : ListAdapter<NamedAPIResource, PokemonTypeAdapter.DataViewHolder>(
    diffUtilCallback
) {

    private var onClick: (NamedAPIResource) -> Unit = {}
    fun setCallback(callback: (NamedAPIResource) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemTypePokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemTypePokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NamedAPIResource) {

            with(binding) {

                textViewType.text = item.name

                imageViewType.setImageDrawable(AppCompatResources.getDrawable(
                    root.context,
                 nameToId(
                     "ic_${item.name}_24",
                    "drawable",
                    c = binding.root.context
                )))

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