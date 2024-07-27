package com.tejasbhong.pokemon.common.data.remote.api.di

import com.tejasbhong.pokemon.common.data.remote.api.PokemonApi
import com.tejasbhong.pokemon.common.data.remote.api.repository.PokemonRepositoryImpl
import com.tejasbhong.pokemon.common.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }
}
