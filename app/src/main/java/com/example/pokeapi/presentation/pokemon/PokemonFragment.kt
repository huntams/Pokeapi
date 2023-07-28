package com.example.pokeapi.presentation.pokemon

import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokeapi.R
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.model.PokemonImages
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.databinding.FragmentFocusPokemonBinding
import com.example.pokeapi.presentation.PokemonEvolutionAdapter
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
            chainLinks.add(chainLink[0])
            pokemonChain(chainLink[0].evolves_to)
        } else
            chainLinks.add(chainLink[0])
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args: PokemonFragmentArgs by navArgs()
        lateinit var pokemonDB: PokemonModel

        viewModel.getPokemonByName(args.pokemonName)
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { pokemon ->
            with(binding) {
                with(pokemon) {
                    textViewNumber.text = "#" + pokemon.id.toString().padStart(3, '0')
                    textViewName.text = pokemon.name

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
                val colorId: Int = requireContext().resources.getIdentifier(
                    it.color.name,
                    "color",
                    requireContext().packageName
                )
                val desiredColor: Int =
                    androidx.core.content.ContextCompat.getColor(requireContext(), colorId)

                pokemonDB = PokemonModel(
                    id = it.id.toLong(),
                    name = it.name,
                    location_area_encounters = textViewInfo.text.toString(),
                    gender_rate = it.gender_rate,
                    capture_rate = it.capture_rate,
                    is_legendary = it.is_legendary,
                    is_mythical = it.is_mythical,
                    color = it.color.name
                )
                appBar.background = desiredColor.toDrawable()
                toolbar.background = desiredColor.toDrawable()
                toolbar.title = it.name
                textViewNumber.text = "#" + it.id.toString().padStart(3, '0')
                textViewName.text = "Capture rate: " + it.capture_rate.toString()
                textViewInfo.text = "Is mythical: " + it.is_mythical
                textViewLegendary.text = "Is legendary: " + it.is_legendary

                viewModel.getPokemonEvolutionById(
                    it.evolution_chain.url
                        .substringAfter("evolution-chain")
                        .replace("/", "").toInt()
                )
            }
        }


        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                else -> {
                    viewModel.addPokemonDB(pokemonDB)
                    false
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}