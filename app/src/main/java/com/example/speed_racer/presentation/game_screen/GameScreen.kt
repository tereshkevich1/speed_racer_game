package com.example.speed_racer.presentation.game_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.speed_racer.R
import com.example.speed_racer.presentation.game_screen.components.AnimatePlayerCar
import com.example.speed_racer.presentation.game_screen.components.AnimateTraffic
import com.example.speed_racer.presentation.game_screen.components.GameInfoBar
import com.example.speed_racer.presentation.game_screen.components.joystick.Joystick
import com.example.speed_racer.presentation.game_screen.components.rememberBitmap
import com.example.speed_racer.presentation.game_screen.mechanics.CollisionManager
import com.example.speed_racer.presentation.game_screen.mechanics.PlayerCarController
import com.example.speed_racer.presentation.game_screen.mechanics.RoadLayoutManager
import com.example.speed_racer.presentation.game_screen.mechanics.RoadMarkingsManager
import com.example.speed_racer.presentation.game_screen.mechanics.TrafficManager
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants
import com.example.speed_racer.presentation.game_screen.util.dpToPx
import com.example.speed_racer.ui.theme.GameScreenBackgroundColor
import com.example.speed_racer.ui.theme.Speed_racerTheme

@Composable
fun GameScreen(
    onGameOver: () -> Unit,
    onNavigateToStartScreen: () -> Unit,
    gameScreenViewModel: GameScreenViewModel = hiltViewModel(),
) {
    val remainingTime by gameScreenViewModel.remainingTime.collectAsState()
    val score by gameScreenViewModel.score.collectAsState()

    val joystickSizePx = dpToPx(GameConstants.JOYSTICK_SIZE)
    val bottomPlayerCarPadding = joystickSizePx + GameConstants.EXTRA_BOTTOM_PADDING

    val screenWidthDp = dpToPx(LocalConfiguration.current.screenWidthDp.dp)
    val screenHeight = dpToPx(LocalConfiguration.current.screenHeightDp.dp)

    val carPainter = painterResource(R.drawable.clipped_police)
    val roadLayout = remember { RoadLayoutManager.calculateLayout(screenWidthDp, carPainter) }

    val trafficManager =
        remember { TrafficManager(screenHeight, roadLayout.carHeight, roadLayout.lanePositions) }

    val roadMarkingsManager = remember {
        RoadMarkingsManager(
            screenHeight = screenHeight,
            roadMarkPositions = roadLayout.roadMarkPositions
        )
    }

    val playerCarController = remember {
        PlayerCarController(
            screenHeight = screenHeight,
            screenWidthDp = screenWidthDp,
            carHeight = roadLayout.carHeight,
            carWidth = roadLayout.carWidth,
            bottomPadding = bottomPlayerCarPadding,
            lanePositions = roadLayout.lanePositions
        )
    }

    val collisionManager = remember {
        CollisionManager(
            playerCarController = playerCarController,
            trafficManager = trafficManager,
            carWidth = roadLayout.carWidth,
            carHeight = roadLayout.carHeight
        )
    }
    collisionManager.onCollision = {
        trafficManager.disableTraffic()
        playerCarController.stopCar()
        gameScreenViewModel.saveBestScore()
        onGameOver()
    }

    collisionManager.onOvertake = {
        gameScreenViewModel.incrementScore()
    }

    if (remainingTime == 0) {
        gameScreenViewModel.saveBestScore()
        onNavigateToStartScreen()
    }

    AnimateTraffic(trafficManager, collisionManager, roadMarkingsManager)
    AnimatePlayerCar(playerCarController)

    val policeBitmap = rememberBitmap(R.drawable.clipped_police)
    val userBitmap = rememberBitmap(R.drawable.clipped_user_car)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GameScreenBackgroundColor),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            roadMarkingsManager.draw(this)

            trafficManager.getCars().forEach { car ->
                drawImage(
                    image = policeBitmap,
                    srcSize = IntSize(policeBitmap.width, policeBitmap.height),
                    dstSize = IntSize(roadLayout.carWidth.toInt(), roadLayout.carHeight.toInt()),
                    dstOffset = IntOffset(car.position.x.toInt(), car.position.y.toInt())
                )
            }

            drawImage(
                image = userBitmap,
                srcSize = IntSize(userBitmap.width, userBitmap.height),
                dstSize = IntSize(roadLayout.carWidth.toInt(), roadLayout.carHeight.toInt()),
                dstOffset = IntOffset(
                    playerCarController.car.position.x.toInt(),
                    playerCarController.car.position.y.toInt()
                )
            )
        }

        GameInfoBar(
            remainingTime = remainingTime,
            score = score,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Joystick(
            onMove = { angle, strength ->
                if (!collisionManager.isCollisionDetected) {
                    playerCarController.onMoveCar(angle, strength)
                }
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
            GameScreen({}, {})
        }
    }
}


