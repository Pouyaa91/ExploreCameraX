package com.example.explorecamerax

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.explorecamerax.domain.Landmark
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var landmarkResult by mutableStateOf(Landmark.EMPTY)
        private set

    fun updateLandmarkResult(landmark: Landmark) {
        landmarkResult = landmark
    }
}