package com.example.pokedexrefactoring.repository

import com.example.pokedexrefactoring.data.remote.manager.PokedexManager
import javax.inject.Inject

interface MainRepository {
    fun fetchPokemonList(page: Int)
}

class MainRepositoryImpl @Inject constructor(private val pokedexManager: PokedexManager) :
    MainRepository {
    override fun fetchPokemonList(page: Int) {
        pokedexManager.fetchPokemonList(page)
    }

}

