package com.example.explorecamerax

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner

@Composable
fun CameraPreviewView(
    modifier: Modifier = Modifier,
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                cameraController.bindToLifecycle(lifecycle)
                controller = cameraController
            }
        },
        modifier = modifier
    )
}