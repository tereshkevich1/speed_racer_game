package com.example.speed_racer.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import com.example.speed_racer.presentation.game_screen.mechanics.PlayerCarController

@Composable
fun AnimatePlayerCar(playerCarController: PlayerCarController) {
    LaunchedEffect(playerCarController.isJoystickActive) {
        var lastFrameTime = 0L
        while (playerCarController.isJoystickActive) {
            withFrameNanos { frameTime ->
                if (lastFrameTime == 0L) lastFrameTime = frameTime
                val deltaTime = (frameTime - lastFrameTime) / 1_000_000_000f
                lastFrameTime = frameTime
                playerCarController.update(deltaTime)
            }
        }
    }
}