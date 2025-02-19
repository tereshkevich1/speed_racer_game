package com.example.speed_racer.presentation.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speed_racer.R
import com.example.speed_racer.presentation.game_screen.util.RoadLayoutManager
import com.example.speed_racer.presentation.game_screen.util.dpToPx
import com.example.speed_racer.ui.theme.Speed_racerTheme
import kotlinx.coroutines.delay


@Composable
fun GameScreen() {
    val screenWidthDp = dpToPx(LocalConfiguration.current.screenWidthDp.dp)
    val screenHeight = dpToPx(LocalConfiguration.current.screenHeightDp.dp)

    val carPainter = painterResource(R.drawable.police)
    val roadLayout = RoadLayoutManager.calculateLayout(screenWidthDp, carPainter)

    val carManager = remember { TrafficManager(screenHeight, roadLayout.carHeight, roadLayout.lanePositions) }

    LaunchedEffect(Unit) {
        var lastUpdateTime = System.nanoTime()
        while (true) {
            val currentTime = System.nanoTime()
            val deltaTime = (currentTime - lastUpdateTime) / 1_000_000_000f
            lastUpdateTime = currentTime

            carManager.update(deltaTime)
            carManager.trySpawnCar()
            delay(12L)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        carManager.getCars().forEach { car ->
            translate(left = car.position.x, top = car.position.y) {
                with(carPainter) {
                    draw(size = Size(roadLayout.carWidth, roadLayout.carHeight))
                }
            }
        }
    }
}


@Composable
@Preview
fun GameScreenPreview() {
    Speed_racerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GameScreen()
        }
    }
}


