package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import com.example.speed_racer.presentation.game_screen.util.CarSpeed
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants
import kotlin.math.cos
import kotlin.math.sin

class PlayerCarController(
    screenHeight: Float,
    screenWidthDp: Float,
    carHeight: Float,
    carWidth: Float,
    bottomPadding: Float,
    private val lanePositions: List<Float>
) {
    private var xSpeed by mutableFloatStateOf(0f)
    private var ySpeed by mutableFloatStateOf(0f)
    var isJoystickActive by mutableStateOf(false)
        private set

    private val initialCarPosition = Offset(
        screenWidthDp / 2 - carWidth / 2,
        screenHeight - bottomPadding - carHeight
    )

    var car by mutableStateOf(
        Car(
            position = initialCarPosition,
            speed = CarSpeed.AVG_SPEED.speed,
            laneIndex = 2
        )
    )

    fun onMoveCar(angle: Float, strength: Float) {
        isJoystickActive = true
        xSpeed = cos(angle) * strength
        ySpeed = sin(angle) * strength
    }

    fun stopCar() {
        isJoystickActive = false
    }

    fun update(deltaTime: Float) {
        val baseSpeed = car.speed
        val yAdjustedSpeed = if (ySpeed > 0) ySpeed * GameConstants.Y_SPEED_BOOST else ySpeed

        val xDelta = restrictToBounds(
            current = car.position.x,
            delta = baseSpeed * GameConstants.X_SPEED_MULTIPLIER * deltaTime * xSpeed,
            min = lanePositions.first(),
            max = lanePositions.last()
        )
        val yDelta = restrictToBounds(
            current = car.position.y,
            delta = baseSpeed * deltaTime * yAdjustedSpeed,
            min = 0f,
            max = initialCarPosition.y
        )

        car = car.copy(
            position = car.position.copy(
                x = car.position.x + xDelta,
                y = car.position.y + yDelta
            )
        )
    }

    private fun restrictToBounds(
        current: Float,
        delta: Float,
        min: Float,
        max: Float
    ): Float {
        val newPosition = current + delta
        return if (newPosition in min..max) delta else 0f
    }
}