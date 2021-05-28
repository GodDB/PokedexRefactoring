package com.example.pokedexrefactoring.di

import com.example.pokedexrefactoring.data.remote.manager.PokedexManager
import com.example.pokedexrefactoring.repository.MainRepository
import com.example.pokedexrefactoring.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(pokedexManager: PokedexManager) : MainRepository{
        return MainRepositoryImpl(pokedexManager)
    }


}