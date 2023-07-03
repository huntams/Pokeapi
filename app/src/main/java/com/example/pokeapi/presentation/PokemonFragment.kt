package com.example.pokeapi.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.pokeapi.R
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.databinding.FragmentFocusPokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PokemonFragment : Fragment(R.layout.fragment_focus_pokemon) {
    private val viewModel by viewModels<PokemonsViewModel>()

    private val binding by viewBinding(FragmentFocusPokemonBinding::bind)
    @Inject
    lateinit var pokemonImageAdapter: PokemonImageAdapter

    @Inject
    lateinit var pokemonEvolutionAdapter:  PokemonEvolutionAdapter

    private var chainLinks: MutableList<ChainLink?> = mutableListOf()

    private fun pokemonChain(chainLink: List<ChainLink>){
        if(chainLink[0].evolves_to.isNotEmpty()){
            Log.i(chainLink[0].species.name,chainLink[0].evolves_to.size.toString())
            chainLinks.add(chainLink[0])
            pokemonChain(chainLink[0].evolves_to)//not null
            }
        else
            //null
            chainLinks.add(chainLink[0])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: PokemonFragmentArgs by navArgs()


        viewModel.getPokemonByName(args.pokemonName)
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            with(binding){
                textViewNumber.text = "#"+ it.id.toString().padStart(3, '0')
                textViewName.text = it.name
                pokemonImageAdapter.submitList(mutableListOf(it.sprites))
                recyclerViewImages.apply {
                    adapter = pokemonImageAdapter
                }

                viewModel.getPokemonSpeciesById(it.id)
                textViewInfo.text = it.location_area_encounters
            }
        }
        viewModel.pokemonEvolutionLiveData.observe(viewLifecycleOwner){evolutionChain->


            pokemonChain(listOf(evolutionChain.chain))

            pokemonEvolutionAdapter.submitList(chainLinks)
            binding.recyclerViewEvolution.apply {
                adapter = pokemonEvolutionAdapter
            }
        }
        viewModel.pokemonSpeciesLiveData.observe(viewLifecycleOwner){
            with(binding){
                textViewInfo.text = it.capture_rate.toString()
                textViewLegendary.text = it.color.name
                viewModel.getPokemonEvolutionById(it.evolution_chain.url
                    .substringAfter("evolution-chain")
                    .replace("/", "").toInt())
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}