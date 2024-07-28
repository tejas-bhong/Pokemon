package com.tejasbhong.pokemon.feature.pokemons.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tejasbhong.pokemon.common.presentation.component.Loading
import com.tejasbhong.pokemon.common.presentation.util.Ui.plus
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemon
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemons
import com.tejasbhong.pokemon.feature.pokemons.presentation.PokemonItem

@Composable
fun PokemonsGrid(
    modifier: Modifier = Modifier,
    visible: Boolean,
    contentPadding: PaddingValues,
    pokemons: Pokemons,
    isPaginating: Boolean,
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
            item {
                Loading(visible = isPaginating)
            }
        }
    }
}
