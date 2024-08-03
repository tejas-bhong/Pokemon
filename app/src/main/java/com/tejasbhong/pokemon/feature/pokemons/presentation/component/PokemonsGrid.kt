package com.tejasbhong.pokemon.feature.pokemons.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tejasbhong.pokemon.R
import com.tejasbhong.pokemon.common.presentation.component.Loading
import com.tejasbhong.pokemon.common.presentation.util.Ui.plus
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemon
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons

@Composable
fun PokemonsGrid(
    modifier: Modifier = Modifier,
    visible: Boolean,
    contentPadding: PaddingValues,
    pokemons: Pokemons,
    isPaginating: Boolean,
    isEnd: Boolean,
    onLoadMore: () -> Unit,
    onPokemonClick: (Int) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.then(modifier),
            columns = GridCells.Adaptive(160.dp),
            contentPadding = contentPadding + PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(
                items = pokemons.pokemons,
                key = { _, pokemon: Pokemon -> pokemon.name },
            ) { index: Int, pokemon: Pokemon ->
                PokemonItem(
                    pokemon = pokemon,
                    onClick = {
                        onPokemonClick(pokemon.getId())
                    },
                )
                if (index == pokemons.pokemons.lastIndex) {
                    onLoadMore()
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Loading(visible = isPaginating)
                }
            }
            if (isEnd) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.end_of_list),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }
        }
    }
}
