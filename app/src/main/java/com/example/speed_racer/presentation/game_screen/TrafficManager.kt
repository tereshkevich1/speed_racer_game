package com.example.speed_racer.presentation.game_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import com.example.speed_racer.presentation.game_screen.util.CarSpeed
import com.example.speed_racer.presentation.game_screen.util.RoadConstants
import com.example.speed_racer.presentation.game_screen.util.RoadConstants.CAR_SPEED_MULTIPLIER
import com.example.speed_racer.presentation.game_screen.util.RoadConstants.SPAWN_PROBABILITY
import kotlin.math.abs
import kotlin.random.Random

class TrafficManager(
    private val screenHeight: Int,
    private val carHeight: Float,
    private val lanePositions: List<Float>
) {
    private val cars = mutableStateListOf<Car>()

    fun getCars(): List<Car> = cars

    fun update(deltaTime: Float) {
        cars.forEachIndexed { index, car ->
            cars[index] = car.copy(
                position = car.position.copy(
                    y = car.position.y + car.speed * deltaTime * CAR_SPEED_MULTIPLIER
                )
            )
        }
        cars.removeAll { it.position.y - carHeight > screenHeight }
    }

    fun trySpawnCar() {
        if (Random.nextFloat() < SPAWN_PROBABILITY) {
            val laneIndex = lanePositions.indices.random()
            if (isSpawnSafe(laneIndex) && !cars.filter { car ->
                    car.laneIndex == laneIndex
                }.any { car ->
                    car.position.y < carHeight
                }) {
                val car = Car(
                    position = Offset(lanePositions[laneIndex], -carHeight),
                    speed = CarSpeed.MAX_SPEED.speed,
                    laneIndex = laneIndex
                )
                cars.add(car)
            }
        }
    }

    private fun isSpawnSafe(
        laneIndex: Int,
        minVerticalGap: Float = carHeight
    ): Boolean {

        // The position from which the new car spawns (off-screen at the top)
        val spawnY = -carHeight

        // For each lane, get the position of the topmost car.
        // If there are no cars in the lane, it is considered free (the value is set to a very high number).
        // For the lane where we want to spawn, simulate the appearance of a new car.
        val laneTopYPositions = (0 until RoadConstants.NUMBER_OF_LANES).map { lane ->
            if (lane == laneIndex) {
                spawnY
            } else {
                cars.filter { it.laneIndex == lane }
                    .minByOrNull { it.position.y }
                    ?.position?.y ?: Float.POSITIVE_INFINITY
            }
        }

        // If at least one lane is free (no car in the spawn zone), spawning is safe
        val accessibleLanes = listOf(laneIndex - 1, laneIndex, laneIndex + 1)
            .filter { it in 0 until RoadConstants.NUMBER_OF_LANES }

        if (accessibleLanes.any { lane ->
                laneTopYPositions[lane] >= carHeight
            }) {
            return true
        }

        // Check the gaps between adjacent lanes based on their order.
        // Here, the order of elements in laneTopYPositions corresponds to the order of lanes (e.g., from left to right).
        for (i in 0 until laneTopYPositions.size - 1) {
            val gap =
                abs(abs(abs(laneTopYPositions[i + 1]) - abs(laneTopYPositions[i])) - carHeight)
            if (gap >= minVerticalGap) {
                return true
            }
        }
        return false
    }
}
