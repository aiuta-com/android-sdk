package com.aiuta.fashionsdk.tryon.core.domain.models.image

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.CompressionConfig
import java.io.ByteArrayOutputStream

public actual class PlatformImage(
    private val bitmap: Bitmap,
) {
    public actual val byteArray: ByteArray by lazy { toByteArray() }

    public actual val imageBitmap: ImageBitmap by lazy { toImageBitmap() }

    private fun toByteArray(): ByteArray {
        val compressionConfig = CompressionConfig.DEFAULT

        val resizedBitmap = if (bitmap.width > compressionConfig.compressedImageMaxSize ||
            bitmap.height > compressionConfig.compressedImageMaxSize
        ) {
            resizeBitmap(bitmap, compressionConfig.compressedImageMaxSize)
        } else {
            bitmap
        }
        val byteArrayOutputStream = ByteArrayOutputStream()


        resizedBitmap.compress(
            Bitmap.CompressFormat.JPEG,
            compressionConfig.compressedImageQuality,
            byteArrayOutputStream,
        )

        return byteArrayOutputStream.toByteArray()
    }

    private fun toImageBitmap(): ImageBitmap {
        val byteArray = byteArray
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
    }

    @SuppressLint("UseKtx")
    private fun resizeBitmap(
        image: Bitmap,
        maxSize: Int,
    ): Bitmap {
        val inWidth: Int = image.width
        val inHeight: Int = image.height

        val outWidth = if (inWidth > inHeight) maxSize else inWidth * maxSize / inHeight
        val outHeight = if (inWidth > inHeight) inHeight * maxSize / inWidth else maxSize

        return Bitmap.createScaledBitmap(image, outWidth, outHeight, false)
    }
}
