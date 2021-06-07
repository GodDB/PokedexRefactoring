package com.example.pokedexrefactoring.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.databinding.FragmentDetailBinding
import com.google.android.material.transition.MaterialContainerTransform

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(pokemon: Pokemon): DetailFragment {
            return DetailFragment().apply {

                arguments = Bundle().apply {
                    putParcelable(POKEMON_KEY, pokemon)
                }
            }
        }

        const val POKEMON_KEY = "pokemon"
    }

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
        binding.apply {
            pokemon = arguments?.getParcelable<Pokemon>(POKEMON_KEY)
        }
    }
}