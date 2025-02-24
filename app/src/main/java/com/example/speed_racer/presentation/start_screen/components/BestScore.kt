package com.example.speed_racer.presentation.start_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.speed_racer.R

@Composable
fun BestScore(bestScoreValue: String) {
    val backgroundColor = colorResource(R.color.best_score_background_color)
    val textStyle = MaterialTheme.typography.displayMedium.copy(
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    val labelStyle = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    val textModifier = Modifier
        .background(backgroundColor)
        .padding(
            horizontal = dimensionResource(R.dimen.best_score_padding_horizontal),
            vertical = dimensionResource(R.dimen.best_score_padding_vertical)
        )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = bestScoreValue,
            style = textStyle,
            modifier = textModifier
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
        Text(
            text = "Best Score",
            style = labelStyle,
            modifier = textModifier
        )
    }
}