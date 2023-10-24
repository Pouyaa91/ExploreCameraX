package com.example.explorecamerax.ui

import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.explorecamerax.LandmarkImageAnalyzer
import com.example.explorecamerax.MainViewModel
import com.example.explorecamerax.data.LandmarkDetector

@Composable
fun LandmarkAnalyzerScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    landmarkDetector: LandmarkDetector
) {

    val analyzer = remember {
        LandmarkImageAnalyzer(
            landmarkDetector = landmarkDetector,
            onResults = (viewModel::updateLandmarkResult)
        )
    }
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                analyzer
            )
        }
    }

    LandmarkAnalyzerView(
        modifier = modifier,
        landmark = viewModel.landmarkResult,
        controller = controller
    )
}