package com.example.speed_racer.presentation.game_over_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.speed_racer.R
import com.example.speed_racer.ui.theme.DpSpSize
import com.example.speed_racer.ui.theme.GameOverScreenBackgroundColor
import com.example.speed_racer.ui.theme.Speed_racerTheme

@Composable
fun GameOverScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GameOverScreenBackgroundColor),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painterResource(R.drawable.game_over_2_1),
            contentDescription = stringResource(R.string.game_over_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = DpSpSize.GameOverImagePaddingBottom)
        )
    }
}

@Composable
@Preview
fun GameOverPreview() {
    Speed_racerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            GameOverScreen()
        }
    }
}