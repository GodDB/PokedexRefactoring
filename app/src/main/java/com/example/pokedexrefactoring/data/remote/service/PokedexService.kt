package com.example.pokedexrefactoring.data.remote.service

import com.example.pokedexrefactoring.data.ApiResponse
import com.example.pokedexrefactoring.data.remote.model.PokemonInfo
import com.example.pokedexrefactoring.data.remote.model.PokemonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Single<PokemonResponse>

    @GET("pokemon/{name}")
    fun fetchPokemonInfo(@Path("name") name: String): Single<PokemonInfo>
}