package com.example.pokeapi.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokeapi.R
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FavouritePokemonsFragment : Fragment(R.layout.fragment_recycler_menu) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}