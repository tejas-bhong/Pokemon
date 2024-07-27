package com.tejasbhong.pokemon.feature.pokemon.domain.usecase

import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails
import com.tejasbhong.pokemon.common.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetails @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(id: Int): PokemonDetails {
        return pokemonRepository.getPokemonDetails(id)
    }
}
