package com.example.pokedexrefactoring.util

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersFacade {
    /**
     * IO thread pool scheduler
     */
    val IO = Schedulers.io()

    /**
     * Computation thread pool scheduler
     */
    val COMPUTATION = Schedulers.computation()

    /**
     * Main Thread scheduler
     */
    val UI = AndroidSchedulers.mainThread()
}