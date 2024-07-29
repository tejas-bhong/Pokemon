package com.tejasbhong.pokemon.common.data.remote.api.repository

import com.tejasbhong.pokemon.common.data.remote.api.PokemonApi
import com.tejasbhong.pokemon.common.data.remote.api.mapper.PokemonMapper
import com.tejasbhong.pokemon.common.domain.repository.PokemonRepository
import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): Pokemons {
        return PokemonMapper.toDomain(api.getPokemons(limit, offset))
    }

    override suspend fun getPokemons(url: String): Pokemons {
        return PokemonMapper.toDomain(api.getPokemons(url))
    }

    override suspend fun getPokemonDetails(id: Int): PokemonDetails {
        return PokemonMapper.toDomain(api.getPokemonDetails(id))
    }
}
