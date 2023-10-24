package com.example.explorecamerax.ui

import android.content.res.Configuration
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DetectionZoneView(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Gray.copy(0.3f),
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "color transition"
    )

    Box(
        modifier = modifier
            .padding(8.dp)
            .size(getDetectionZoneSize(LocalConfiguration.current))
            .border(2.dp, color, shape = RoundedCornerShape(16.dp))
    )
}

private fun getDetectionZoneSize(configuration: Configuration): Dp {
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenHeightDp.dp
    } else {
        configuration.screenWidthDp.dp
    }
}



