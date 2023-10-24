package com.example.explorecamerax.ui

import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.explorecamerax.CameraPreviewView
import com.example.explorecamerax.domain.Landmark

@Composable
fun LandmarkAnalyzerView(
    modifier: Modifier = Modifier,
    landmark: Landmark,
    controller: LifecycleCameraController,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        CameraPreviewView(
            modifier = Modifier.fillMaxSize(),
            cameraController = controller,
            lifecycle = LocalLifecycleOwner.current
        )

        DetectionZoneView(modifier = Modifier.align(Alignment.Center))

        AnimatedVisibility(
            visible = isValidResult(landmark),
            enter = slideInVertically(),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = landmark.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

private fun isValidResult(landmark: Landmark): Boolean {
    return landmark.name.isNotBlank() && landmark.precision > 0.75
}