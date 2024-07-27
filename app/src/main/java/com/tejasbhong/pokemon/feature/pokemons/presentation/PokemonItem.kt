package com.tejasbhong.pokemon.feature.pokemons.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemon

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        onClick = { onClick() },
    ) {
        Column {
            val asyncImagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.getPhoto())
                    .allowConversionToBitmap(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .build()
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F),
                painter = asyncImagePainter,
                contentDescription = pokemon.name,
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview
@Composable
private fun PokemonItemPrev() {
    PokemonItem(
        pokemon = Pokemon(),
        onClick = {},
    )
}
