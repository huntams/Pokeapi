package com.example.pokeapi.presentation.evolution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokeapi.R
import com.example.pokeapi.data.remote.model.EvolutionChain
import com.example.pokeapi.databinding.FragmentRecyclerEvolutionBinding
import com.example.pokeapi.presentation.pokemon.EvolutionsAdapter
import com.example.pokeapi.presentation.pokemon.PokemonEvolutionAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class EvolutionsFragment: Fragment(R.layout.fragment_recycler_evolution) {


    @Inject
    lateinit var evolutionsAdapter: EvolutionsAdapter
    private val viewModel by viewModels<EvolutionViewModel>()
    private val binding by viewBinding(FragmentRecyclerEvolutionBinding::bind)
    private val evolutions = mutableListOf<EvolutionChain>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var number = 1
        val limit = 20
        viewModel.getPokemonEvolutionById(number)
        viewModel.pokemonEvolutionLiveData.observe(viewLifecycleOwner) { result ->
            evolutions.add(result)
            number += 1
            if(number<limit)
                viewModel.getPokemonEvolutionById(number)
            else{
                with(binding){
                    Toast.makeText(requireContext(),evolutions.size.toString(),Toast.LENGTH_SHORT).show()
                    evolutionsAdapter.submitList(evolutions)
                    recyclerView.apply {
                        adapter = evolutionsAdapter
                    }

                }
            }
        }

    }

}