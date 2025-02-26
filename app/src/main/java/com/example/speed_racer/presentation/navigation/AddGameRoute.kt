package com.example.speed_racer.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.speed_racer.presentation.game_over_screen.GameOverScreen
import com.example.speed_racer.presentation.game_screen.GameScreen
import com.example.speed_racer.presentation.navigation.util.navigateSingleTop
import com.example.speed_racer.presentation.start_screen.StartScreen

fun NavGraphBuilder.addGameRoute(navController: NavHostController) {
    navigation<GameDestinations.GameNav>(startDestination = GameDestinations.StartScreen) {
        startScreenDestination(navController)
        gameScreenDestination(navController)
        gameOverScreenDestination(navController)
    }
}

fun NavGraphBuilder.startScreenDestination(navController: NavHostController) {
    composable<GameDestinations.StartScreen> {
        StartScreen(onStartGameButtonClick = {
            navController.navigateSingleTop(GameDestinations.GameScreen)
        })
    }
}

fun NavGraphBuilder.gameScreenDestination(navController: NavHostController) {
    composable<GameDestinations.GameScreen> {
        GameScreen(onGameOver = { navController.navigateSingleTop(GameDestinations.GameOverScreen) },
            onNavigateToStartScreen = { navController.navigateSingleTop(GameDestinations.StartScreen) })
    }
}

fun NavGraphBuilder.gameOverScreenDestination(navController: NavHostController) {
    composable<GameDestinations.GameOverScreen> {
        GameOverScreen(onNavigateToStartScreen = { navController.navigateSingleTop(GameDestinations.StartScreen) })
    }
}