package com.tejasbhong.pokemon.feature.pokemon.presentation

sealed interface UiEvent {
    data class Load(val id: Int) : UiEvent
    data object RetryLoading : UiEvent
}
