package com.example.pokeapi.presentation.favourite

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.PokemonWithSpritesModel
import com.example.pokeapi.data.remote.model.NamedAPIResource
import com.example.pokeapi.databinding.ItemDbPokemonBinding
import com.example.pokeapi.databinding.ItemPokemonBinding
import javax.inject.Inject

class FavouriteAdapter @Inject constructor() :
    ListAdapter<PokemonWithSpritesModel, FavouriteAdapter.NoteViewHolder>(diffUtil) {
    private var onClick: (PokemonWithSpritesModel) -> Unit = {}
    fun setCallback(callback: (PokemonWithSpritesModel) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemDbPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(
        private var binding: ItemDbPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonWithSpritesModel) {
            with(binding) {
                root.setOnClickListener {
                    onClick.invoke(item)
                }

                val reqContext = root.context
                val colorId: Int = reqContext.resources.getIdentifier(
                    item.pokemonEntity.color,
                    "color",
                    reqContext.packageName
                )
                val desiredColor: Int =
                    androidx.core.content.ContextCompat.getColor(reqContext, colorId)
                root.background = desiredColor.toDrawable()
                textViewNumber.text = item.pokemonEntity.id.toString()

                textViewName.text = item.pokemonEntity.name.toString()
                imageViewPokemon.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        item.sprites.last().sprite,
                        0,
                        item.sprites.last().sprite.size
                    )
                )
                /*
                Glide.with(itemView)
                    .load(item.uri)
                    .override(100)
                    .into(closeImageView)


                closeImageView.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        item.uri,
                        0,
                        item.uri.size
                    )
                )

                 */
            }
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<PokemonWithSpritesModel>() {

    override fun areContentsTheSame(
        oldItem: PokemonWithSpritesModel,
        newItem: PokemonWithSpritesModel
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: PokemonWithSpritesModel,
        newItem: PokemonWithSpritesModel
    ) = oldItem.pokemonEntity.id == newItem.pokemonEntity.id
}
