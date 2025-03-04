package com.example.speed_racer.presentation.game_screen.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun dpToPx(dpValue: Dp): Float {
    val density = LocalDensity.current
    return with(density) { dpValue.toPx() }
}

