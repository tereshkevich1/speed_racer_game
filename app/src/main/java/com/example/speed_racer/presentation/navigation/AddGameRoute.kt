package com.example.speed_racer.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.speed_racer.presentation.game_over_screen.GameOverScreen
import com.example.speed_racer.presentation.game_screen.GameScreen
import com.example.speed_racer.presentation.start_screen.StartScreen

fun NavGraphBuilder.addGameRoute(navController: NavHostController) {
    navigation<GameDestinations.GameNav>(startDestination = GameDestinations.StartScreen) {
        startScreenDestination(navController)
        gameScreenDestination()
        gameOverScreenDestination()
    }
}

fun NavGraphBuilder.startScreenDestination(navController: NavHostController) {
    composable<GameDestinations.StartScreen> {
        StartScreen(onStartGameButtonClick = { navController.navigate(GameDestinations.GameScreen) })
    }
}

fun NavGraphBuilder.gameScreenDestination() {
    composable<GameDestinations.GameScreen> {
        GameScreen()
    }
}

fun NavGraphBuilder.gameOverScreenDestination() {
    composable<GameDestinations.GameOverScreen> {
        GameOverScreen()
    }
}