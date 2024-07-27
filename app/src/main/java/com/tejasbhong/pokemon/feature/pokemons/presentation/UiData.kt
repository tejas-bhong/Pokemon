package com.tejasbhong.pokemon.feature.pokemons.presentation

import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons

data class UiData(
    val pokemons: Pokemons = Pokemons(),
)
