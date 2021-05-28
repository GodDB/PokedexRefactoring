package com.example.pokedexrefactoring.data.remote.model

data class Pokemon(
    var page: Int = 0,
    val name: String,
    val url: String
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}
