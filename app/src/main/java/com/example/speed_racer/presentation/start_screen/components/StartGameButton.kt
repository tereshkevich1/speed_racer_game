package com.example.speed_racer.presentation.start_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.speed_racer.R

@Composable
fun StartGameButton(onClick: () -> Unit) {
    val backgroundButtonColor = colorResource(R.color.start_game_background_color)
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundButtonColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.button_height))
            .padding(horizontal = dimensionResource(R.dimen.button_padding_horizontal))
    ) {
        Text(
            text = stringResource(R.string.start_game_button),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}