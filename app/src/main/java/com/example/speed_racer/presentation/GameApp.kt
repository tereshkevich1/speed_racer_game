package com.example.speed_racer.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.speed_racer.presentation.navigation.AppNavGraph
import com.example.speed_racer.ui.theme.Speed_racerTheme

@Composable
fun GameApp() {
    val navController = rememberNavController()
    Speed_racerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavGraph(
                navHostController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}