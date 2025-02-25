package com.example.speed_racer.presentation.navigation

import kotlinx.serialization.Serializable

sealed class GameDestinations {
    @Serializable
    data object GameNav : GameDestinations()

    @Serializable
    data object StartScreen : GameDestinations()

    @Serializable
    data object GameScreen : GameDestinations()

    @Serializable
    data object GameOverScreen : GameDestinations()
}
