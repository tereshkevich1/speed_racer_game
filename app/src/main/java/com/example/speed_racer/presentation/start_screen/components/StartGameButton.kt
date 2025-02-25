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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.speed_racer.R
import com.example.speed_racer.ui.theme.DpSpSize
import com.example.speed_racer.ui.theme.StartGameBackgroundColor

@Composable
fun StartGameButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(containerColor = StartGameBackgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(DpSpSize.ButtonHeight)
            .padding(horizontal = DpSpSize.ButtonPaddingHorizontal)
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