package com.example.pokedexrefactoring.ui.main

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedexrefactoring.BaseViewModel
import com.example.pokedexrefactoring.data.remote.model.Pokemon
import com.example.pokedexrefactoring.data.remote.model.PokemonResponse
import com.example.pokedexrefactoring.repository.MainRepository
import com.example.pokedexrefactoring.util.Event
import com.example.pokedexrefactoring.util.SchedulersFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel() {

    // inputs
    private val selectedPokemon: PublishSubject<Pair<Int, Pokemon>> = PublishSubject.create()
    private val _pageIndex: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)

    // outputs

    var isPagingFinished: Boolean = false

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _showPokemonDetail: MutableLiveData<Event<Pair<Int, Pokemon>>> = MutableLiveData()
    val showPokemonDetail: LiveData<Event<Pair<Int, Pokemon>>>
        get() = _showPokemonDetail

    init {

        val pokemonResponse = _pageIndex.distinctUntilChanged()
            .switchMap { fetchPokemonList(it) }

        compositeDisposable += pokemonResponse
            .filter { it.isOnNext }
            .map { it.value?.results }
            .subscribe { handlePokemonList(it!!) }

        compositeDisposable += pokemonResponse
            .filter { it.isOnError }
            .map { it.error?.message }
            .subscribe(_errorMsg::setValue)

        compositeDisposable += selectedPokemon.throttleFirst(300, TimeUnit.MILLISECONDS)
            .map { Event(it) }
            .subscribe(_showPokemonDetail::setValue, Throwable::printStackTrace)

    }

    @MainThread
    fun fetchPokemonList() {
        _pageIndex.onNext(_pageIndex.value?.plus(1) ?: 0)
    }

    fun onSelectPokemonItem(index: Int, pokemon: Pokemon) {
        selectedPokemon.onNext(index to pokemon)
    }

    private fun fetchPokemonList(page: Int): Observable<Notification<PokemonResponse>> {
        return mainRepository.fetchPokemonList(page)
            .observeOn(SchedulersFacade.UI)
            .doOnSubscribe { _isLoading.value = true }
            .doOnEvent { t1, t2 -> _isLoading.value = false }
            .materialize()
            .toObservable()
    }

    private fun handlePokemonList(pokemons: List<Pokemon>) {
        if (pokemons.isEmpty()) {
            isPagingFinished = true
        } else {
            val cachedList = _pokemonList.value ?: emptyList()
            _pokemonList.value = cachedList.toMutableList().apply { addAll(pokemons) }
        }
    }
}

