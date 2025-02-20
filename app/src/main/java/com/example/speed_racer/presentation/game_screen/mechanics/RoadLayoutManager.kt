package com.example.speed_racer.presentation.game_screen.mechanics

import androidx.compose.ui.graphics.painter.Painter
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.CAR_WIDTH_RATIO
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.HORIZONTAL_PADDING
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.NUMBER_OF_LANES
import com.example.speed_racer.presentation.game_screen.util.constants.RoadConfig.ROAD_MARKING_WIDTH

object RoadLayoutManager {
    /**
     * Calculates the road layout parameters based on the screen width and car parameters.
     */
    fun calculateLayout(screenWidth: Float, carPainter: Painter): RoadLayout {
        val roadLaneWidth =
            (screenWidth - (HORIZONTAL_PADDING * 2 + (NUMBER_OF_LANES + 1) * ROAD_MARKING_WIDTH)) / NUMBER_OF_LANES
        val carWidth = roadLaneWidth * CAR_WIDTH_RATIO
        val aspectRatio = carPainter.intrinsicSize.height / carPainter.intrinsicSize.width
        val carHeight = carWidth * aspectRatio

        val firstLaneCenter =
            HORIZONTAL_PADDING + ROAD_MARKING_WIDTH + (roadLaneWidth) / 2 - carWidth / 2
        val laneSpacing = ROAD_MARKING_WIDTH + roadLaneWidth

        val lanePositions = List(NUMBER_OF_LANES) { index ->
            firstLaneCenter + laneSpacing * index
        }

        return RoadLayout(roadLaneWidth, carWidth, carHeight, firstLaneCenter, laneSpacing, lanePositions)
    }
}
