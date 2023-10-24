package com.example.explorecamerax.data

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import org.tensorflow.lite.task.vision.classifier.ImageClassifier.ImageClassifierOptions
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageAnalyzerModule {
    @Provides
    @Singleton
    fun provideImageProcessor(): ImageProcessor = ImageProcessor.Builder().build()

    @Provides
    @Singleton
    fun provideImageProcessingOptions(): ImageProcessingOptions =
        ImageProcessingOptions.builder()
            .build()

    @Provides
    @Singleton
    fun provideBaseOptions(): BaseOptions = BaseOptions.builder().setNumThreads(THREADS).build()

    @Provides
    @Singleton
    fun provideImageClassifierOptions(baseOptions: BaseOptions): ImageClassifierOptions =
        ImageClassifierOptions
            .builder()
            .setBaseOptions(baseOptions)
            .setMaxResults(MAX_RESULTS)
            .setScoreThreshold(THRESHOLD)
            .build()

    @Provides
    @Singleton
    fun provideImageClassifier(
        @ApplicationContext context: Context,
        classifierOptions: ImageClassifierOptions
    ): ImageClassifier? =
        try {
            ImageClassifier.createFromFileAndOptions(
                context,
                MODEL_PATH,
                classifierOptions
            )
        } catch (e: Exception) {
            Log.e("Classifier", e.message.orEmpty())
            null
        }

    private const val THREADS = 2
    private const val MAX_RESULTS = 1
    private const val THRESHOLD = 0.7f
    private const val MODEL_PATH = "landmarks_asia_V1_1.tflite"

}