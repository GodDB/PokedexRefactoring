package com.example.pokedexrefactoring.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.pokedexrefactoring.R
import com.example.pokedexrefactoring.base.BindingActivity
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            adapter = PokemonAdapter(viewModel)
        }

        startObserve()
    }

    private fun startObserve() {
        viewModel.errorMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


}