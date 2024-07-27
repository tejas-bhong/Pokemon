package com.tejasbhong.pokemon.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tejasbhong.pokemon.common.presentation.component.PokemonTopAppBar
import com.tejasbhong.pokemon.core.presentation.theme.PokémonTheme
import com.tejasbhong.pokemon.feature.pokemon.presentation.PokemonScreen
import com.tejasbhong.pokemon.feature.pokemons.presentation.PokemonsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokémonTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        PokemonTopAppBar(
                            isBackVisible = navBackStackEntry?.destination?.route != "pokemons",
                            onBackClick = { navController.popBackStack() },
                        )
                    },
                ) { innerPadding ->
                    val scope = rememberCoroutineScope()
                    NavHost(
                        navController = navController,
                        startDestination = "pokemons",
                    ) {
                        composable(route = "pokemons") {
                            PokemonsScreen(
                                contentPadding = innerPadding,
                                onPokemonClick = { id ->
                                    scope.launch {
                                        delay(250)
                                        navController.navigate("pokemon/$id")
                                    }
                                },
                            )
                        }
                        composable(
                            route = "pokemon/{id}",
                            arguments = listOf(navArgument(name = "id") { type = NavType.IntType }),
                        ) { backStackEntry ->
                            val pokemonId = backStackEntry.arguments?.getInt("id")
                                ?: return@composable
                            PokemonScreen(contentPadding = innerPadding, id = pokemonId)
                        }
                    }
                }
            }
        }
    }
}