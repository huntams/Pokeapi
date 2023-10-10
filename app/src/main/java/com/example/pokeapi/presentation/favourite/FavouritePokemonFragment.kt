package com.example.pokeapi.presentation.favourite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokeapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePokemonFragment : Fragment(R.layout.fragment_single_favourite_pokemon) {

    private val viewModel by viewModels<FavouritePokemonViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args: FavouritePokemonsFragmentArgs by navArgs()
        viewModel.getPokemonWithSprites(args.pokemonId)
        viewModel.pokemonSpritesDBLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.pokemonEntity.name,Toast.LENGTH_SHORT).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}