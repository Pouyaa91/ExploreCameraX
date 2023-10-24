package com.example.explorecamerax.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface LandmarkDetectorModule {
    @Binds
    fun provideLandmarkDetector(impl: LandmarkDetectorImpl): LandmarkDetector
}