package com.example.explorecamerax.data

import android.graphics.Bitmap

fun Bitmap.centerCrop(desiredWidth: Int, desiredHeight: Int): Bitmap {

    val startX = (width - desiredWidth) / 2
    val startY = (height - desiredHeight) / 2

    return Bitmap.createBitmap(this, startX, startY, desiredWidth, desiredHeight)
}
