package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.ui.geometry.Offset

data class Car(
    var id: Int,
    var position: Offset,
    val speed: Float,
    var laneIndex: Int,
)