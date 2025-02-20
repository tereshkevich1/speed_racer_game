package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.ui.geometry.Offset

data class Car(
    var position: Offset,
    val speed: Float,
    val laneIndex: Int,
)