package com.tejasbhong.pokemon.common.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.tejasbhong.pokemon.R

@Composable
fun ErrorRetry(
    visible: Boolean,
    onRetryClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.an_unknown_error_occurred))
            Button(
                onClick = {
                    onRetryClick()
                },
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}
