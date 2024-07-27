package com.tejasbhong.pokemon.feature.pokemons.presentation

sealed interface UiEvent {
    data object LoadMorePokemons : UiEvent
    data object RetryLoading : UiEvent
}
