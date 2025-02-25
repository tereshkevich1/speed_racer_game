package com.example.speed_racer.presentation.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.speed_racer.R
import com.example.speed_racer.presentation.start_screen.components.BestScore
import com.example.speed_racer.presentation.start_screen.components.StartGameButton
import com.example.speed_racer.ui.theme.DpSpSize
import com.example.speed_racer.ui.theme.Speed_racerTheme

@Composable
fun StartScreen(onStartGameButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.horizon_chase_bp_1),
                contentScale = ContentScale.Crop
            )
    ) {
        Image(
            painterResource(R.drawable.car_title),
            contentDescription = stringResource(R.string.game_title_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = DpSpSize.TopGameTitlePadding)
        )

        Spacer(modifier = Modifier.weight(1f))
        BestScore("23")
        Spacer(modifier = Modifier.weight(1.9f))

        StartGameButton(onClick = onStartGameButtonClick)
        Spacer(modifier = Modifier.weight(1.6f))
    }
}

@Composable
@Preview
fun StartScreenPreview() {
    Speed_racerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            StartScreen{}
        }
    }
}