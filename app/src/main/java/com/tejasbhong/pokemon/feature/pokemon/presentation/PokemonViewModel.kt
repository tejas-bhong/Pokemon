package com.tejasbhong.pokemon.feature.pokemon.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejasbhong.pokemon.feature.pokemon.domain.usecase.GetPokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonDetails: GetPokemonDetails,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiData = MutableStateFlow(UiData())
    val uiData = _uiData.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiData(),
    )
    private val _uiState = Channel<UiState>()
    val uiState = _uiState.receiveAsFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading,
    )

    fun onUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.Load -> handleLoadPokemonDetails(uiEvent.id)
            is UiEvent.RetryLoading -> handleRetryLoading()
        }
    }

    private fun handleRetryLoading() {
        handleLoadPokemonDetails(savedStateHandle["id"]!!)
    }

    private fun handleLoadPokemonDetails(id: Int) {
        savedStateHandle["id"] = id
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.trySend(UiState.Loading)
                _uiData.update { uiData ->
                    uiData.copy(
                        pokemon = getPokemonDetails(id)
                    )
                }
                _uiState.trySend(UiState.Idle)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.trySend(UiState.ErrorPokemonLoading)
            }
        }
    }
}
