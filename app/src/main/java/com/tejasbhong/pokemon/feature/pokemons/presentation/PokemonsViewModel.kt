package com.tejasbhong.pokemon.feature.pokemons.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons
import com.tejasbhong.pokemon.feature.pokemons.domain.usecase.GetPokemons
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
class PokemonsViewModel @Inject constructor(
    private val getPokemons: GetPokemons,
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

    init {
        fetchPokemons(initial = true)
    }

    fun onUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.LoadMorePokemons -> handleLoadMorePokemons()
            is UiEvent.RetryLoading -> handleRetryLoading()
        }
    }

    private fun handleRetryLoading() {
        fetchPokemons(initial = true)
    }

    private fun handleLoadMorePokemons() {
        fetchPokemons(offset = _uiData.value.pokemons.pokemons.size)
    }

    private fun fetchPokemons(initial: Boolean = false, limit: Int = 20, offset: Int = 0) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.trySend(if (initial) UiState.Loading else UiState.Paginating)
                _uiData.update { uiData ->
                    val pokemonsList = uiData.pokemons.pokemons.toMutableList()
                    val pokemons = if (initial) {
                        getPokemons(limit, offset)
                    } else {
                        val nextUrl = _uiData.value.pokemons.next
                        if (nextUrl != null) {
                            getPokemons(
                                limit,
                                nextUrl.split("offset=").last().split("&").first().toInt()
                            )
                        } else {
                            _uiState.trySend(UiState.Idle)
                            return@launch
                        }
                    }
                    pokemonsList.addAll(pokemons.pokemons)
                    uiData.copy(
                        pokemons = Pokemons(
                            count = pokemons.count,
                            next = pokemons.next,
                            previous = pokemons.previous,
//                            pokemons = pokemonsList.distinctBy { it.name },
                            pokemons = pokemonsList,
                        )
                    )
                }
                _uiState.trySend(UiState.Idle)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.trySend(UiState.ErrorPokemonsLoading)
            }
        }
    }
}
