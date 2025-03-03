package com.example.speed_racer.presentation.navigation.util

import androidx.navigation.NavHostController
import com.example.speed_racer.presentation.navigation.GameDestinations

fun NavHostController.navigateSingleTop(route: GameDestinations) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(route) {
            inclusive = true
        }
    }
}