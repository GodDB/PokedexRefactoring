package com.example.pokedexrefactoring.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.pokedexrefactoring.R
import com.example.pokedexrefactoring.base.BindingActivity
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.databinding.ActivityMainBinding
import com.example.pokedexrefactoring.ui.detail.DetailFragment
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@MainActivity
        }

        setupMainFragment()
    }

    private fun setupMainFragment() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, MainFragment.newInstance { v, pokemon ->
                showDetailFragment(v, pokemon)
            })
        }
    }

    private fun showDetailFragment(transitionView: View, pokemon: Pokemon) {

        supportFragmentManager.commit {
            addSharedElement(transitionView, transitionView.transitionName)
            replace(binding.fragmentContainer.id, DetailFragment.newInstance(pokemon).apply {
                sharedElementEnterTransition = MaterialContainerTransform().apply {
                    duration = 500
                    interpolator = FastOutSlowInInterpolator()
                    fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
                    scrimColor = Color.TRANSPARENT
                }
                sharedElementReturnTransition = MaterialContainerTransform().apply {
                    duration = 500
                    interpolator = FastOutSlowInInterpolator()
                    fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
                    scrimColor = Color.TRANSPARENT
                }
            })
            addToBackStack(null)
        }



    }

}