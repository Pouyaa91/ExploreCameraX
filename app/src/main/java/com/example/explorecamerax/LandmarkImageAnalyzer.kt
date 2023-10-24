package com.example.explorecamerax

import android.os.SystemClock
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.explorecamerax.data.LandmarkDetector
import com.example.explorecamerax.data.centerCrop
import com.example.explorecamerax.domain.Landmark

class LandmarkImageAnalyzer(
    private val landmarkDetector: LandmarkDetector,
    private val onResults: (Landmark) -> Unit
) : ImageAnalysis.Analyzer {

    private var lastAnalyzedTime = 0L

    override fun analyze(image: ImageProxy) {
        analyzeImage(image)

        image.close()
    }

    private fun analyzeImage(image: ImageProxy) {
        val currentTime = SystemClock.uptimeMillis()
        if (shouldAnalyzeImage(currentTime).not()) return

        val rotationDegrees = image.imageInfo.rotationDegrees
        val bitmap =
            image.toBitmap().centerCrop(MODEL_REQUIRED_IMAGE_SIZE, MODEL_REQUIRED_IMAGE_SIZE)
        val results = landmarkDetector.detect(bitmap, rotationDegrees)
        onResults(results)
        lastAnalyzedTime = currentTime
    }

    private fun shouldAnalyzeImage(currentTime: Long): Boolean {
        return currentTime - lastAnalyzedTime >= IMAGE_ANALYZE_INTERVAL_MILLIS
    }

    companion object {
        private const val IMAGE_ANALYZE_INTERVAL_MILLIS = 1000L
        private const val MODEL_REQUIRED_IMAGE_SIZE = 321
    }
}