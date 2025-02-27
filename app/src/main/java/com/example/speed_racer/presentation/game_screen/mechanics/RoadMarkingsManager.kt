package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.ROAD_MARKING_HEIGHT
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.ROAD_MARKING_SPACING
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.ROAD_MARKING_SPEED
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.ROAD_MARKING_WIDTH
import com.example.speed_racer.ui.theme.RoadMarkColor
import kotlin.math.ceil

class RoadMarkingsManager(
    private val screenHeight: Float,
    private val roadMarkPositions: List<Float>,
) {
    private var markingsOffset by mutableFloatStateOf(0f)
    private val markingTotalHeight = ROAD_MARKING_HEIGHT + ROAD_MARKING_SPACING
    private val numberOfMarkings =
        ceil(screenHeight / markingTotalHeight).toInt() + 2

    init {
        markingsOffset = -markingTotalHeight
    }

    fun update(deltaTime: Float) {
        markingsOffset += ROAD_MARKING_SPEED * deltaTime
        if (markingsOffset >= 0f) {
            markingsOffset -= markingTotalHeight
        }
    }

    fun draw(drawScope: DrawScope) {
        for (i in 0 until numberOfMarkings) {
            val yPos = markingsOffset + i * markingTotalHeight
            if (yPos + ROAD_MARKING_HEIGHT > -markingTotalHeight && yPos < screenHeight) {
                drawMarkingsAtY(drawScope, yPos)
            }
        }
    }

    private fun drawMarkingsAtY(drawScope: DrawScope, y: Float) {
        roadMarkPositions.forEach { markX ->
            drawScope.drawRect(
                color = RoadMarkColor,
                topLeft = Offset(x = markX, y = y),
                size = Size(ROAD_MARKING_WIDTH / 2, ROAD_MARKING_HEIGHT)
            )
        }
    }
}