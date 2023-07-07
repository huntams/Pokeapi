package com.example.pokeapi.presentation.pokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentRecyclerMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_recycler_menu) {

    private val binding by viewBinding(FragmentRecyclerMenuBinding::bind)

    private val viewModel by viewModels<PokemonsViewModel>()

    @Inject
    lateinit var pokemonsPagingAdapter: PokemonsPagingAdapter

    @Inject
    lateinit var pokemonImageAdapter: PokemonImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPokemons()
        viewModel.pokemonsLiveData.observe(viewLifecycleOwner) { result ->
            /*
            when (result) {
                is ResultLoader.Success ->
                    pokemonsPagingAdapter.apply {
                        submitData(viewLifecycleOwner.lifecycle, result.value)

                        setCallback {
                            viewModel.getPokemonByName(it.name)
                        }
                    }
                is ResultLoader.Failure -> Toast.makeText(
                    requireContext(),
                    result.throwable.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                else -> {}
            }
             */
            pokemonsPagingAdapter.apply {
                submitData(viewLifecycleOwner.lifecycle, result)

                setCallback {
                    findNavController().navigate(
                        PokemonsFragmentDirections.actionPokemonsFragmentToPokemonFragment(
                            it.name
                        )
                    )
                }
            }




        }
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { result ->
            /*
            when (result) {
                is ResultLoader.Failure -> Toast.makeText(
                    requireContext(),
                    result.throwable.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                else -> {}
            }

             */
        }
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = pokemonsPagingAdapter
        }
        super.onViewCreated(view, savedInstanceState)
    }
}