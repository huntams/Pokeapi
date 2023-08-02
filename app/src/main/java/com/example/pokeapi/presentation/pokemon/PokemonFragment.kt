package com.example.pokeapi.presentation.pokemon

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.pokeapi.R
import com.example.pokeapi.data.db.PokemonModel
import com.example.pokeapi.data.db.model.SpriteDBEntity
import com.example.pokeapi.data.model.PokemonImages
import com.example.pokeapi.data.remote.model.ChainLink
import com.example.pokeapi.databinding.FragmentFocusPokemonBinding
import com.example.pokeapi.presentation.PokemonEvolutionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
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
    private var images: MutableList<PokemonImages> = mutableListOf()
    private val byteArray = mutableListOf<ByteArray>()
    private fun pokemonChain(chainLink: List<ChainLink>) {
        if (chainLink[0].evolves_to.isNotEmpty()) {
            chainLinks.add(chainLink[0])
            pokemonChain(chainLink[0].evolves_to)
        } else
            chainLinks.add(chainLink[0])
    }

    private var item: Any? = null
    lateinit var pokemonDB: PokemonModel
    private fun Bitmap.convertToByteArray(): ByteArray = ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, 50, this)
    }.toByteArray()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: PokemonFragmentArgs by navArgs()
        viewModel.getPokemonByName(args.pokemonName)
        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { pokemon ->
            with(binding) {
                with(pokemon) {
                    textViewNumber.text = "#" + pokemon.id.toString().padStart(3, '0')
                    images.add(PokemonImages(1, sprites.front_default))
                    images.add(PokemonImages(2, sprites.front_female))
                    images.add(PokemonImages(3, sprites.front_shiny))
                    images.add(PokemonImages(4, sprites.front_shiny_female))
                    images.add(PokemonImages(5, sprites.back_default))
                    images.add(PokemonImages(6, sprites.back_female))
                    images.add(PokemonImages(7, sprites.back_shiny))
                    images.add(PokemonImages(8, sprites.back_shiny_female))
                }

                images = images.filter {
                    it.url != null
                }.toMutableList()

                images.map {
                    lifecycleScope.launch {
                        val loader = ImageLoader(requireContext())
                        val request = ImageRequest.Builder(requireContext())
                            .data(it.url)
                            .allowHardware(false) // Disable hardware bitmaps.
                            .build()
                        val result = (loader.execute(request) as SuccessResult).drawable
                        val bitmap = (result as BitmapDrawable).bitmap
                        byteArray.add(bitmap.convertToByteArray())
                    }
                }.also {
                    viewModel.getPokemonSpeciesById(pokemon.id)
                }
                textViewName.text = pokemon.name
                pokemonImageAdapter.submitList(images)
                recyclerViewImages.apply {
                    adapter = pokemonImageAdapter
                }
                textViewInfo.text = pokemon.location_area_encounters
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
                appBar.background = desiredColor.toDrawable()
                toolbar.background = desiredColor.toDrawable()
                toolbar.title = it.name
                textViewNumber.text = "#" + it.id.toString().padStart(3, '0')
                textViewName.text = "Capture rate: " + it.capture_rate.toString()
                textViewInfo.text = "Is mythical: " + it.is_mythical
                textViewLegendary.text = "Is legendary: " + it.is_legendary
                textViewLegendary.text = byteArray.size.toString()
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

                viewModel.getPokemonEvolutionById(
                    it.evolution_chain.url
                        .substringAfter("evolution-chain")
                        .replace("/", "").toInt()
                )
            }
        }

        viewModel.pokemonEvolutionLiveData.observe(viewLifecycleOwner) { evolutionChain ->
            pokemonChain(listOf(evolutionChain.chain))
            pokemonEvolutionAdapter.submitList(chainLinks)
            binding.recyclerViewEvolution.apply {
                adapter = pokemonEvolutionAdapter
            }
            viewModel.getPokemonDB(pokemonDB.id)
        }
        viewModel.pokemonDBLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.toolbar.menu.findItem(R.id.actionFavourite)
                    .setIcon(R.drawable.ic_favorite_border_24)
                Toast.makeText(requireContext(), pokemonDB.name + " deleted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.toolbar.menu.findItem(R.id.actionFavourite)
                    .setIcon(R.drawable.ic_favorite_24)
                Toast.makeText(requireContext(), pokemonDB.name + " saved", Toast.LENGTH_SHORT)
                    .show()
            }
            item = it
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setOnMenuItemClickListener {

            when (it.itemId) {
                else -> {
                    if (item != null) {
                        viewModel.deletePokemonDB(pokemonDB)
                    } else {
                        viewModel.addSprite(SpriteDBEntity(pokemonEntityId = pokemonDB.id, sprite = byteArray.last()))
                        viewModel.addPokemonDB(pokemonDB)

                    }
                    /*
                    Toast.makeText(requireContext(),pokemonDB.name,Toast.LENGTH_LONG).show()
                    viewModel.getPokemonsDB()
                    viewModel.pokemonsDBLiveData.observe(viewLifecycleOwner) { pokemons ->
                        item = pokemons.find {
                            it.id == pokemonDB.id
                        }
                        binding.textViewInfo.text = item.toString()
                        if (item != null) {
                            Toast.makeText(requireContext(),pokemonDB.name+" удалён",Toast.LENGTH_SHORT).show()
                            viewModel.deletePokemonDB(pokemonDB)
                        } else {
                            Toast.makeText(requireContext(),pokemonDB.name+" сохранён",Toast.LENGTH_SHORT).show()
                            viewModel.addPokemonDB(pokemonDB)
                        }
                    }

                     */



                    true
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}