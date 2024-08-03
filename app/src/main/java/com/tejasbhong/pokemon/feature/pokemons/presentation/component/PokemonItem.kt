package com.tejasbhong.pokemon.feature.pokemons.presentation.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tejasbhong.pokemon.R
import com.tejasbhong.pokemon.feature.pokemons.domain.model.Pokemon

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: () -> Unit,
) {
    val cardColors = if (pokemon.backgroundColor == null) CardDefaults.cardColors()
    else CardDefaults.cardColors(containerColor = Color(pokemon.backgroundColor))
    val textColor = if (pokemon.titleColor == null) MaterialTheme.colorScheme.onSurface
    else Color(pokemon.titleColor)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        colors = cardColors,
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F),
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = asyncImagePainter,
                    contentDescription = pokemon.name,
                )
                if (asyncImagePainter.state !is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .size(56.dp)
                            .align(Alignment.Center),
                        painter = rememberAsyncImagePainter(model = R.drawable.pokemon),
                        contentDescription = pokemon.name,
                    )
                }
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
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
