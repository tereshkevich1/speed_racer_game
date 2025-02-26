package com.example.speed_racer.presentation.navigation.util

import androidx.navigation.NavHostController

fun <T : Any> NavHostController.navigateSingleTop(route: T) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(route){
            inclusive = false
        }
    }
}