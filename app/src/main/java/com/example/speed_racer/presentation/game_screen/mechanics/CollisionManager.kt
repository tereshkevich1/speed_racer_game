package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants.COLLISION_HORIZONTAL_PADDING
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants.COLLISION_VERTICAL_PADDING

class CollisionManager(
    private val playerCarController: PlayerCarController,
    private val trafficManager: TrafficManager,
    private val carWidth: Float,
    private val carHeight: Float
) {
    private val collisionPadding = COLLISION_HORIZONTAL_PADDING
    private val verticalCollisionPadding = COLLISION_VERTICAL_PADDING
    var isCollisionDetected by mutableStateOf(false)
        private set

    var onCollision: (() -> Unit)? = null

    fun checkCollisions() {
        if (!trafficManager.isTrafficEnabled) {
            isCollisionDetected = false
            return
        }

        val playerCar = playerCarController.car
        val trafficCars = trafficManager.getCars()

        isCollisionDetected = trafficCars.any { trafficCar ->
            isCollision(
                playerX = playerCar.position.x,
                playerY = playerCar.position.y,
                trafficX = trafficCar.position.x,
                trafficY = trafficCar.position.y
            )
        }

        if (isCollisionDetected) {
            onCollision?.invoke()
        }
    }

    private fun isCollision(
        playerX: Float,
        playerY: Float,
        trafficX: Float,
        trafficY: Float
    ): Boolean {
        val playerLeft = playerX + collisionPadding
        val playerRight = playerX + carWidth - collisionPadding
        val playerTop = playerY + verticalCollisionPadding
        val playerBottom = playerY + carHeight - verticalCollisionPadding

        val trafficLeft = trafficX + collisionPadding
        val trafficRight = trafficX + carWidth - collisionPadding
        val trafficTop = trafficY - collisionPadding
        val trafficBottom = trafficY + carHeight + collisionPadding

        return (playerLeft < trafficRight &&
                playerRight > trafficLeft &&
                playerTop < trafficBottom &&
                playerBottom > trafficTop)
    }

    fun reset() {
        isCollisionDetected = false
    }
}