package com.example.pokedexrefactoring.data.remote.manager

import com.example.pokedexrefactoring.data.ApiResponse
import com.example.pokedexrefactoring.data.remote.model.PokemonInfo
import com.example.pokedexrefactoring.data.remote.model.PokemonResponse
import com.example.pokedexrefactoring.data.remote.service.PokedexService
import io.reactivex.Single
import javax.inject.Inject

class PokedexManager @Inject constructor(private val pokeService: PokedexService) {

    fun fetchPokemonList(
        page: Int
    ): Single<PokemonResponse> =
        pokeService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    fun fetchPokemonInfo(
        name: String
    ): Single<PokemonInfo> =
        pokeService.fetchPokemonInfo(
            name = name
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}
