package com.tejasbhong.pokemon.feature.pokemon.presentation.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tejasbhong.pokemon.feature.pokemon.domain.model.Ability

@Composable
fun PokemonAbilities(
    abilities: List<Ability>,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        abilities.forEach { ability ->
            SuggestionChip(
                shape = MaterialTheme.shapes.extraLarge,
                onClick = {},
                label = { Text(text = ability.name) },
            )
        }
    }
}
