package com.tejasbhong.pokemon.feature.pokemons.presentation

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data object Paginating : UiState
    data object ErrorPokemonsLoading : UiState
}
