package com.tejasbhong.pokemon.feature.pokemon.presentation.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tejasbhong.pokemon.R

@Composable
fun PokemonPhoto(
    modifier: Modifier = Modifier,
    id: Int,
    photo: String,
    name: String,
) {
    Card(modifier = Modifier.then(modifier)) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            val asyncImagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo)
                    .allowConversionToBitmap(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .build()
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = asyncImagePainter,
                contentDescription = name,
            )
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd),
                text = stringResource(id = R.string.id, id),
                style = MaterialTheme.typography.displaySmall,
            )
        }
    }
}
