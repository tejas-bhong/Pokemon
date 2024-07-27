package com.tejasbhong.pokemon.feature.pokemon.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonName(
    name: String,
) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = name,
        style = MaterialTheme.typography.displayLarge,
    )
}
