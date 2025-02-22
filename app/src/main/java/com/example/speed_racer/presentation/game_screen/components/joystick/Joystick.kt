package com.example.speed_racer.presentation.game_screen.components.joystick

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.speed_racer.R
import com.example.speed_racer.presentation.game_screen.util.constants.Colors
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants
import com.example.speed_racer.presentation.game_screen.util.dpToPx
import kotlin.math.atan2
import kotlin.math.hypot

@Composable
fun Joystick(
    onMove: (angle: Float, strength: Float) -> Unit,
    onEndMove: () -> Unit,
    size: Dp = GameConstants.JOYSTICK_SIZE,
    bottomPadding: Dp = dimensionResource(R.dimen.bottom_joystick_padding)
) {
    val sizePx = dpToPx(size)
    val center = Offset(sizePx / 2, sizePx / 2)
    var knobPosition by remember { mutableStateOf(center) }
    var isDragging by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(bottom = bottomPadding)) {
        Canvas(modifier = Modifier
            .size(size)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {},
                    onDrag = { change, dragAmount ->
                        change.consume()
                        isDragging = true

                        val (newPosition, angle, strength) = calculateNewPosition(
                            center = center,
                            currentPosition = knobPosition,
                            dragDelta = dragAmount,
                            maxDistance = sizePx / 2 * GameConstants.JOYSTICK_DRAG_MULTIPLIER
                        )

                        knobPosition = newPosition
                        onMove(angle, strength)
                    },
                    onDragEnd = {
                        isDragging = false
                        knobPosition = center
                        onEndMove()
                    }
                )
            })
        {
            drawCircle(
                color = Colors.JoystickBase,
                radius = sizePx / 2,
                style = Fill
            )

            drawCircle(
                color = if (isDragging) Colors.JoystickActive else Colors.JoystickIdle,
                radius = sizePx / 4,
                center = knobPosition,
                style = Fill
            )
        }
    }
}

private fun calculateNewPosition(
    center: Offset,
    currentPosition: Offset,
    dragDelta: Offset,
    maxDistance: Float
): Triple<Offset, Float, Float> {
    val newPosition = currentPosition + dragDelta
    val delta = newPosition - center
    val distance = hypot(delta.x, delta.y)

    val knobPosition = if (distance > maxDistance) {
        center + delta * (maxDistance / distance)
    } else {
        newPosition
    }

    val angle = atan2(delta.y, delta.x)
    val strength = (distance / maxDistance).coerceAtMost(1f)

    return Triple(knobPosition, angle, strength)
}