package com.example.speed_racer.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import com.example.speed_racer.presentation.game_screen.mechanics.CollisionManager
import com.example.speed_racer.presentation.game_screen.mechanics.RoadMarkingsManager
import com.example.speed_racer.presentation.game_screen.mechanics.TrafficManager

@Composable
fun AnimateTraffic(
    trafficManager: TrafficManager,
    collisionManager: CollisionManager,
    roadMarkingsManager: RoadMarkingsManager
) {
    LaunchedEffect(trafficManager.isTrafficEnabled) {
        var lastFrameTime = 0L
        while (trafficManager.isTrafficEnabled) {
            withFrameNanos { frameTime ->
                if (lastFrameTime == 0L) lastFrameTime = frameTime
                val deltaTime = (frameTime - lastFrameTime) / 1_000_000_000f
                lastFrameTime = frameTime
                roadMarkingsManager.update(deltaTime)
                trafficManager.update(deltaTime)
                trafficManager.trySpawnCar()
                collisionManager.checkCollisions()
            }
        }
    }
}