package com.example.speed_racer.presentation.game_screen.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberBitmap(resourceId: Int): ImageBitmap {
    val context = LocalContext.current
    val resources = context.resources
    return remember(resourceId, resources) {
        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        BitmapFactory.decodeResource(resources, resourceId, options).asImageBitmap()
    }
}