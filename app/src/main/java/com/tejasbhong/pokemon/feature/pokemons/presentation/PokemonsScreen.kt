package com.tejasbhong.pokemon.feature.pokemons.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tejasbhong.pokemon.common.presentation.component.ErrorRetry
import com.tejasbhong.pokemon.common.presentation.component.Loading
import com.tejasbhong.pokemon.feature.pokemons.presentation.component.PokemonsGrid

@Composable
fun PokemonsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    onPokemonClick: (Int) -> Unit,
    viewModel: PokemonsViewModel = hiltViewModel(),
) {
    val uiData by viewModel.uiData.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    PokemonsScreen(
        modifier = modifier,
        contentPadding = contentPadding,
        uiData = uiData,
        uiState = uiState,
        onUiEvent = { viewModel.onUiEvent(it) },
        onPokemonClick = onPokemonClick,
    )
}

@Composable
private fun PokemonsScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    uiData: UiData,
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit,
    onPokemonClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        ErrorRetry(
            visible = uiState is UiState.ErrorPokemonsLoading,
            onRetryClick = {
                onUiEvent(UiEvent.RetryLoading)
            },
        )
        Loading(visible = uiState is UiState.Loading)
        PokemonsGrid(
            visible = uiState !is UiState.ErrorPokemonsLoading,
            contentPadding = contentPadding,
            pokemons = uiData.pokemons.pokemons,
            isPaginating = uiState is UiState.Paginating,
            onLoadMore = { onUiEvent(UiEvent.LoadMorePokemons) },
            onPokemonClick = onPokemonClick,
        )
    }
}

@Preview
@Composable
private fun PokemonsScreenPrev() {
    PokemonsScreen(
        contentPadding = PaddingValues(),
        uiData = UiData(),
        uiState = UiState.Idle,
        onUiEvent = {},
        onPokemonClick = {},
    )
}
