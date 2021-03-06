package com.example.pokedexrefactoring.data

class ApiResponse<T> {
    var code = 0
    var message: String? = null
    var result: T? = null
        private set

    fun setResult(result: T) {
        this.result = result
    }

    val isOk: Boolean
        get() = code == 0

}
