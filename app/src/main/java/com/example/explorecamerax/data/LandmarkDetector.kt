package com.example.explorecamerax.data

import android.graphics.Bitmap
import com.example.explorecamerax.domain.Landmark

fun interface LandmarkDetector {
    fun detect(bitmap: Bitmap, rotation: Int): Landmark
}