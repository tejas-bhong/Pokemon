package com.tejasbhong.pokemon.common.data.remote.api.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.tejasbhong.pokemon.common.data.remote.api.model.PokemonDetailsResponse
import com.tejasbhong.pokemon.common.data.remote.api.model.PokemonsResponse
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemon
import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons

object PokemonMapper {
    fun toDomain(pokemonsResponse: PokemonsResponse): Pokemons {
        return Pokemons(
            count = pokemonsResponse.count,
            next = pokemonsResponse.next,
            previous = pokemonsResponse.previous,
            pokemons = pokemonsResponse.pokemonResponses.map { pokemonResponse ->
                Pokemon(
                    name = pokemonResponse.name.capitalize(Locale.current),
                    url = pokemonResponse.url,
                )
            },
        )
    }

    fun toDomain(pokemonDetailsResponse: PokemonDetailsResponse): PokemonDetails {
        return PokemonDetails(
            id = pokemonDetailsResponse.id,
            name = pokemonDetailsResponse.name.capitalize(Locale.current),
            photo = pokemonDetailsResponse.sprites.other.officialArtwork.frontDefault,
            height = pokemonDetailsResponse.height / 10F,
            weight = pokemonDetailsResponse.weight / 10F,
            baseExperience = pokemonDetailsResponse.baseExperience,
            abilities = pokemonDetailsResponse.abilities.map { abilityResponse ->
                AbilityMapper.toDomain(abilityResponse)
            },
        )
    }
}
