package com.example.speed_racer.presentation.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speed_racer.R
import com.example.speed_racer.presentation.game_screen.components.AnimatePlayerCar
import com.example.speed_racer.presentation.game_screen.components.AnimateTraffic
import com.example.speed_racer.presentation.game_screen.components.joustick.Joystick
import com.example.speed_racer.presentation.game_screen.mechanics.PlayerCarController
import com.example.speed_racer.presentation.game_screen.mechanics.TrafficManager
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants
import com.example.speed_racer.presentation.game_screen.mechanics.RoadLayoutManager
import com.example.speed_racer.presentation.game_screen.util.dpToPx
import com.example.speed_racer.ui.theme.Speed_racerTheme

@Composable
fun GameScreen() {
    val joystickSizePx = dpToPx(GameConstants.JOYSTICK_SIZE)
    val bottomPlayerCarPadding = joystickSizePx + GameConstants.EXTRA_BOTTOM_PADDING

    val screenWidthDp = dpToPx(LocalConfiguration.current.screenWidthDp.dp)
    val screenHeight = dpToPx(LocalConfiguration.current.screenHeightDp.dp)

    val carPainter = painterResource(R.drawable.police)
    val userCarPainter = painterResource(R.drawable.user_car_3)
    val roadLayout = RoadLayoutManager.calculateLayout(screenWidthDp, carPainter)

    val trafficManager =
        remember { TrafficManager(screenHeight, roadLayout.carHeight, roadLayout.lanePositions) }

    val playerCarController = remember {
        PlayerCarController(
            screenHeight = screenHeight,
            screenWidthDp = screenWidthDp,
            carHeight = roadLayout.carHeight,
            carWidth = roadLayout.carWidth,
            bottomPadding = bottomPlayerCarPadding
        )
    }

    AnimateTraffic(trafficManager)
    AnimatePlayerCar(playerCarController)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            trafficManager.getCars().forEach { car ->
                translate(left = car.position.x, top = car.position.y) {
                    with(carPainter) {
                        draw(size = Size(roadLayout.carWidth, roadLayout.carHeight))
                    }
                }
            }
            translate(
                left = playerCarController.car.position.x,
                top = playerCarController.car.position.y
            ) {
                with(userCarPainter) {
                    draw(size = Size(roadLayout.carWidth, roadLayout.carHeight))
                }
            }
        }

        Joystick(
            onMove = { angle, strength ->
                playerCarController.onMoveCar(angle, strength)
            },
            onEndMove = {
                playerCarController.stopCar()
            }
        )
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


