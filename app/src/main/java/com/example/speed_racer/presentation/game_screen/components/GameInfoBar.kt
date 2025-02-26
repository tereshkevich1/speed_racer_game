package com.example.speed_racer.presentation.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.speed_racer.R
import com.example.speed_racer.ui.theme.DpSpSize

@Composable
fun GameInfoBar(remainingTime: Int, score: Int, modifier: Modifier) {
    val textStyle = MaterialTheme.typography.bodyLarge
    val textColor = MaterialTheme.colorScheme.onBackground
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = DpSpSize.GameInfoPaddingTop,
                start = DpSpSize.GameInfoPaddingHorizontal,
                end = DpSpSize.GameInfoPaddingHorizontal
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.game_time, remainingTime / 60, remainingTime % 60),
            style = textStyle,
            color = textColor
        )
        Text(
            text = stringResource(R.string.game_score, score),
            style = textStyle,
            color = textColor
        )
    }
}
