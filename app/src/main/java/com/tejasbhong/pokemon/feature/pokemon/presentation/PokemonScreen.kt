package com.tejasbhong.pokemon.feature.pokemon.presentation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tejasbhong.pokemon.common.presentation.component.ErrorRetry
import com.tejasbhong.pokemon.common.presentation.component.Loading
import com.tejasbhong.pokemon.common.presentation.util.Ui.plus
import com.tejasbhong.pokemon.feature.pokemon.domain.model.PokemonDetails
import com.tejasbhong.pokemon.feature.pokemon.presentation.component.PokemonAbilities
import com.tejasbhong.pokemon.feature.pokemon.presentation.component.PokemonChars
import com.tejasbhong.pokemon.feature.pokemon.presentation.component.PokemonName
import com.tejasbhong.pokemon.feature.pokemon.presentation.component.PokemonPhoto

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    id: Int,
    viewModel: PokemonViewModel = hiltViewModel(),
) {
    val uiData by viewModel.uiData.collectAsState()
    val pokemonDetails = uiData.pokemon
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = id) {
        viewModel.onUiEvent(UiEvent.Load(id))
    }
    PokemonScreen(
        modifier = modifier,
        contentPadding = contentPadding,
        pokemonDetails = pokemonDetails,
        uiState = uiState,
    )
}

@Composable
private fun PokemonScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    pokemonDetails: PokemonDetails,
    uiState: UiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        ErrorRetry(
            visible = uiState is UiState.ErrorPokemonLoading,
            onRetryClick = {},
        )
        Loading(visible = uiState is UiState.Loading)
        AnimatedVisibility(
            visible = uiState is UiState.Idle,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Column(
                    modifier = Modifier
                        .verticalScroll(state = rememberScrollState())
                        .padding(contentPadding + PaddingValues(16.dp))
                        .fillMaxSize()
                        .then(modifier),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PokemonPhoto(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1F),
                        id = pokemonDetails.id,
                        photo = pokemonDetails.photo,
                        name = pokemonDetails.name,
                    )
                    PokemonName(pokemonDetails.name)
                    PokemonAbilities(pokemonDetails.abilities)
                    PokemonChars(
                        height = pokemonDetails.height,
                        weight = pokemonDetails.weight,
                        baseExperience = pokemonDetails.baseExperience,
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(contentPadding + PaddingValues(16.dp))
                        .fillMaxSize()
                        .then(modifier),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PokemonPhoto(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1F),
                        id = pokemonDetails.id,
                        photo = pokemonDetails.photo,
                        name = pokemonDetails.name,
                    )
                    Column(
                        modifier = Modifier.weight(1F),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        PokemonName(pokemonDetails.name)
                        PokemonAbilities(pokemonDetails.abilities)
                        PokemonChars(
                            height = pokemonDetails.height,
                            weight = pokemonDetails.weight,
                            baseExperience = pokemonDetails.baseExperience,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true, showBackground = true
)
@Preview(
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    showSystemUi = true, showBackground = true
)
@Composable
private fun PokemonDetailsScreenPrev() {
    PokemonScreen(
        contentPadding = PaddingValues(),
        pokemonDetails = PokemonDetails(),
        uiState = UiState.Idle,
    )
}
