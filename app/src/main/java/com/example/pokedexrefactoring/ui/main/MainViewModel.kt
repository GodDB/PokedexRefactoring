package com.example.pokedexrefactoring.ui.main

import com.example.pokedexrefactoring.BaseViewModel
import com.example.pokedexrefactoring.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {
}