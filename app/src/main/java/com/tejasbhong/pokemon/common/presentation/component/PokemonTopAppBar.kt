package com.tejasbhong.pokemon.common.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tejasbhong.pokemon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(
    modifier: Modifier = Modifier,
    isBackVisible: Boolean,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            AnimatedVisibility(visible = isBackVisible) {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = TopAppBarDefaults.topAppBarColors().containerColor.copy(
                alpha = 0.8F
            ),
            scrolledContainerColor = TopAppBarDefaults.topAppBarColors().scrolledContainerColor.copy(
                alpha = 0.8F
            ),
        ),
    )
}
