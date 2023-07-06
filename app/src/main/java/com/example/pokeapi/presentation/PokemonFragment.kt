package com.example.pokeapi.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.pokeapi.R
import com.example.pokeapi.data.model.Pokemon
import com.example.pokeapi.data.model.PokemonImages
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.databinding.ActivityMainBinding
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
    lateinit var pokemonEvolutionAdapter: PokemonEvolutionAdapter

    private var chainLinks: MutableList<ChainLink?> = mutableListOf()
    private val images: MutableList<PokemonImages> = mutableListOf()

    private fun pokemonChain(chainLink: List<ChainLink>) {
        if (chainLink[0].evolves_to.isNotEmpty()) {
            Log.i(chainLink[0].species.name, chainLink[0].evolves_to.size.toString())
            chainLinks.add(chainLink[0])
            pokemonChain(chainLink[0].evolves_to)//not null
        } else
        //null
            chainLinks.add(chainLink[0])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: PokemonFragmentArgs by navArgs()

        val res = requireContext().getResources()
        val packageName = requireContext().packageName



        viewModel.getPokemonByName(args.pokemonName)
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { pokemon ->
            with(binding) {
                textViewNumber.text = "#" + pokemon.id.toString().padStart(3, '0')
                textViewName.text = pokemon.name
                with(pokemon) {
                    images.add(PokemonImages(1, sprites.front_default))
                    images.add(PokemonImages(2, sprites.front_female))
                    images.add(PokemonImages(3, sprites.front_shiny))
                    images.add(PokemonImages(4, sprites.front_shiny_female))
                    images.add(PokemonImages(5, sprites.back_default))
                    images.add(PokemonImages(6, sprites.back_female))
                    images.add(PokemonImages(7, sprites.back_shiny))
                    images.add(PokemonImages(8, sprites.back_shiny_female))


                }
                pokemonImageAdapter.submitList(images)
                recyclerViewImages.apply {
                    adapter = pokemonImageAdapter
                }


                viewModel.getPokemonSpeciesById(pokemon.id)
                textViewInfo.text = pokemon.location_area_encounters
            }
        }
        viewModel.pokemonEvolutionLiveData.observe(viewLifecycleOwner) { evolutionChain ->


            pokemonChain(listOf(evolutionChain.chain))

            pokemonEvolutionAdapter.submitList(chainLinks)
            binding.recyclerViewEvolution.apply {
                adapter = pokemonEvolutionAdapter
            }
        }
        viewModel.pokemonSpeciesLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                val colorId: Int = res.getIdentifier(it.color.name, "color", packageName)
                val desiredColor: Int = ContextCompat.getColor(requireContext(), colorId)
                appBar.background = desiredColor.toDrawable()
                toolbar.background = desiredColor.toDrawable()
                toolbar.title = it.name
                textViewInfo.text = it.capture_rate.toString()
                textViewLegendary.text = it.color.name
                viewModel.getPokemonEvolutionById(
                    it.evolution_chain.url
                        .substringAfter("evolution-chain")
                        .replace("/", "").toInt()
                )
            }
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}