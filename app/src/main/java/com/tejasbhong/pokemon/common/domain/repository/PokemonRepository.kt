package com.tejasbhong.pokemon.common.domain.repository

import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons

interface PokemonRepository {
    suspend fun getPokemons(
        limit: Int,
        offset: Int,
    ): Pokemons
    suspend fun getPokemons(
        url: String,
    ): Pokemons

    suspend fun getPokemonDetails(id: Int): PokemonDetails
}
