package com.example.pokeapi.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentFavouritePokemonBinding
import com.example.pokeapi.databinding.FragmentFocusPokemonBinding
import com.example.pokeapi.presentation.pokemon.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FavouritePokemonsFragment : Fragment(R.layout.fragment_recycler_menu) {

    private val viewModel by viewModels<PokemonsViewModel>()

    private val binding by viewBinding(FragmentFavouritePokemonBinding::bind)

    @Inject
    lateinit var favouriteAdapter: FavouriteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.getPokemonsWithSprites()
            //viewModel.getPokemonsDB()
            viewModel.pokemonsSpritesDBLiveData.observe(viewLifecycleOwner){pokemons->
                favouriteAdapter.submitList(pokemons)
                recyclerView.apply {
                    adapter = favouriteAdapter
                }
            }
            viewModel.pokemonsDBLiveData.observe(viewLifecycleOwner) { pokemons ->

            }
        }
    }
}