package com.tejasbhong.pokemon.feature.pokemons.domain.model

data class Pokemon(
    val name: String = "Pokemon",
    val url: String = "",
) {
    fun getId(): Int {
        return url.split("/").dropLast(1).last().toInt()
    }

    fun getPhoto(): String {
        val id = getId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
