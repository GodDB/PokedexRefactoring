package com.example.pokedexrefactoring.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.pokedexrefactoring.R
import com.example.pokedexrefactoring.base.BindingActivity
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
        }

    }
}