package com.tejasbhong.pokemon.feature.pokemon.domain.model

data class PokemonDetails(
    val id: Int = -1,
    val name: String = "Pokemon",
    val photo: String = "",
    val height: Float = 0F, // Metre
    val weight: Float = 0F, // KG
    val baseExperience: Int = 0,
    val abilities: List<Ability> = emptyList(),
)