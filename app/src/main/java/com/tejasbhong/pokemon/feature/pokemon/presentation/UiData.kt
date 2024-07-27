package com.tejasbhong.pokemon.feature.pokemon.presentation

import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails

data class UiData(
    val pokemon: PokemonDetails = PokemonDetails(),
)
