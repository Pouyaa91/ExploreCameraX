package com.example.explorecamerax.data

import android.graphics.Bitmap
import com.example.explorecamerax.domain.Landmark
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import javax.inject.Inject

class LandmarkDetectorImpl @Inject constructor(
    private val imageProcessor: ImageProcessor,
    private val imageProcessingOptions: ImageProcessingOptions,
    private val classifier: ImageClassifier?
) : LandmarkDetector {

    override fun detect(bitmap: Bitmap, rotation: Int): Landmark {

        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

        val results = classifier?.classify(tensorImage, imageProcessingOptions)?.toList()

        return results
            ?.flatMap { classifications -> classifications.categories }
            ?.map { category -> Landmark(name = category.displayName, precision = category.score) }
            ?.firstOrNull() ?: Landmark.EMPTY
    }
}