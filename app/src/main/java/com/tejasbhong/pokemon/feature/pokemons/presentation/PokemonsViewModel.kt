package com.tejasbhong.pokemon.feature.pokemons.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons
import com.tejasbhong.pokemon.feature.pokemons.domain.usecase.GetPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val getPokemons: GetPokemons,
) : ViewModel() {
    private val _uiData = MutableStateFlow(UiData())
    val uiData = _uiData.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiData(),
    )
    private val _uiState = MutableSharedFlow<UiState>(extraBufferCapacity = 1)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading,
    )

    init {
        fetchPokemons()
    }

    fun onUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.LoadMorePokemons -> handleLoadMorePokemons()
            is UiEvent.RetryLoading -> handleRetryLoading()
        }
    }

    private fun handleRetryLoading() {
        fetchPokemons()
    }

    private fun handleLoadMorePokemons() {
        fetchMorePokemons()
    }

    private fun fetchMorePokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nextUrl = _uiData.value.pokemons.next
                if (nextUrl == null) {
                    _uiState.tryEmit(UiState.EndOfList)
                    return@launch
                }
                _uiState.tryEmit(UiState.Paginating)
                val pokemonsList = _uiData.value.pokemons.pokemons.toMutableList()
                val pokemons = getPokemons(nextUrl)
                pokemonsList.addAll(pokemons.pokemons)
                _uiData.update { uiData ->
                    uiData.copy(pokemons = pokemons.copy(pokemons = pokemonsList))
                }
                _uiState.tryEmit(UiState.Idle)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.tryEmit(UiState.ErrorPokemonsLoading)
            }
        }
    }

    private fun fetchPokemons(limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.tryEmit(UiState.Loading)
                _uiData.update { uiData ->
                    val pokemons = getPokemons(limit, offset)
                    uiData.copy(
                        pokemons = Pokemons(
                            count = pokemons.count,
                            next = pokemons.next,
                            previous = pokemons.previous,
                            pokemons = pokemons.pokemons,
                        )
                    )
                }
                _uiState.tryEmit(UiState.Idle)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.tryEmit(UiState.ErrorPokemonsLoading)
            }
        }
    }
}
