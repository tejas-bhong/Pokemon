package com.tejasbhong.pokemon.feature.pokemons.domain.model

data class Pokemons(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val pokemons: List<Pokemon> = emptyList(),
)
