package com.example.speed_racer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = GameDestinations.GameNav,
        enterTransition = { enterFadeTransaction() },
        exitTransition = { exitFadeTransaction() },
        popEnterTransition = { enterFadeTransaction() },
        popExitTransition = { exitFadeTransaction() }
    ) {
        addGameRoute(navHostController)
    }
}