package com.tejasbhong.pokemon.feature.pokemon.presentation

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data object ErrorPokemonLoading : UiState
}
