package com.example.pokedexrefactoring.repository

import com.example.pokedexrefactoring.data.remote.manager.PokedexManager
import com.example.pokedexrefactoring.data.remote.model.PokemonResponse
import com.example.pokedexrefactoring.util.SchedulersFacade
import io.reactivex.Single
import javax.inject.Inject

interface MainRepository {
    fun fetchPokemonList(page: Int): Single<PokemonResponse>
}

class MainRepositoryImpl @Inject constructor(private val pokedexManager: PokedexManager) :
    MainRepository {
    override fun fetchPokemonList(page: Int): Single<PokemonResponse> {
        return pokedexManager.fetchPokemonList(page)
            .subscribeOn(SchedulersFacade.IO)
    }

}

