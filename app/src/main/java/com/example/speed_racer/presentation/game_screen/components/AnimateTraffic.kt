package com.example.speed_racer.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import com.example.speed_racer.presentation.game_screen.mechanics.TrafficManager

@Composable
fun AnimateTraffic(trafficManager: TrafficManager) {
    LaunchedEffect(trafficManager.isTrafficEnabled) {
        var lastFrameTime = 0L
        while (trafficManager.isTrafficEnabled) {
            withFrameNanos { frameTime ->
                if (lastFrameTime == 0L) lastFrameTime = frameTime
                val deltaTime = (frameTime - lastFrameTime) / 1_000_000_000f
                lastFrameTime = frameTime
                trafficManager.update(deltaTime)
                trafficManager.trySpawnCar()
            }
        }
    }
}