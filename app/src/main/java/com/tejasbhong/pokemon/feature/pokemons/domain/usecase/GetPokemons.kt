package com.tejasbhong.pokemon.feature.pokemons.domain.usecase

import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons
import com.tejasbhong.pokemon.common.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(limit: Int, offset: Int): Pokemons {
        return pokemonRepository.getPokemons(limit, offset)
    }
}
