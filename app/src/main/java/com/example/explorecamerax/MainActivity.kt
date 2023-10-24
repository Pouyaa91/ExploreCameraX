package com.example.explorecamerax

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.explorecamerax.data.LandmarkDetector
import com.example.explorecamerax.ui.LandmarkAnalyzerScreen
import com.example.explorecamerax.ui.theme.ExploreCameraXTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var landmarkDetector: LandmarkDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setKeepScreenOn()
        requestCameraPermission()
        setContent {
            ExploreCameraXTheme {
                LandmarkAnalyzerScreen(
                    landmarkDetector = landmarkDetector
                )
            }
        }
    }

    private fun setKeepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun requestCameraPermission() {
        // TODO: Replace with the proper way for requesting permissions
        if (hasCameraPermission()) return
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 0)
    }


    private fun hasCameraPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }
}