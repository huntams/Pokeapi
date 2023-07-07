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
import com.example.pokeapi.databinding.FragmentRecyclerEvolutionBinding
import com.example.pokeapi.presentation.pokemon.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EvolutionsFragment: Fragment(R.layout.fragment_recycler_evolution) {


    private val viewModel by viewModels<EvolutionViewModel>()
    private val binding by viewBinding(FragmentRecyclerEvolutionBinding::bind)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getEvolutions()
        viewModel.evolutionsLiveData.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireContext(),result.toString(),Toast.LENGTH_LONG).show()

        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}