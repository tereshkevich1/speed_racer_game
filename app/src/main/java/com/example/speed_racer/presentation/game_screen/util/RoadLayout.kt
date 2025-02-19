package com.example.speed_racer.presentation.game_screen.util

data class RoadLayout(
    val roadLaneWidth: Float,
    val carWidth: Float,
    val carHeight: Float,
    val firstLaneCenter: Float,
    val laneSpacing: Float,
    val lanePositions: List<Float>
)