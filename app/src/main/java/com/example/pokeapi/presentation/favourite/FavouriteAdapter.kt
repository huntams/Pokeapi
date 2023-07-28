package com.example.pokeapi.presentation.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.databinding.ItemPokemonBinding
import javax.inject.Inject

class FavouriteAdapter @Inject constructor() :
    ListAdapter<PokemonModel, FavouriteAdapter.NoteViewHolder>(diffUtil) {
    private var onNoteClick: (PokemonModel) -> Unit = {}
    private var onNoteLongClick: (PokemonModel) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(
        private var binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PokemonModel) {
            with(binding) {
                root.setOnClickListener { onNoteClick(item) }
                root.setOnLongClickListener {
                    onNoteLongClick(item)
                    true
                }
                textViewNumber.text = item.id.toString()

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

private val diffUtil = object : DiffUtil.ItemCallback<PokemonModel>() {

    override fun areContentsTheSame(oldItem: PokemonModel, newItem: PokemonModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: PokemonModel, newItem: PokemonModel) = oldItem.id == newItem.id
}
