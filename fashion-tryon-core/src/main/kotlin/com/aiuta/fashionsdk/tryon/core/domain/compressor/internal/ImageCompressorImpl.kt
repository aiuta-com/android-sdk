package com.aiuta.fashionsdk.tryon.core.domain.compressor.internal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import com.aiuta.fashionsdk.tryon.core.domain.compressor.ImageCompressor
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionConfig
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionResult
import com.aiuta.fashionsdk.tryon.core.utils.generateFile
import com.aiuta.fashionsdk.tryon.core.utils.writeByteArray
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

/**
 * [ImageCompressorImpl] - implementation of [ImageCompressor]
 */
internal class ImageCompressorImpl(
    private val context: Context,
) : ImageCompressor {
    override suspend fun compressImageAndResize(
        fileName: String,
        fileUri: Uri,
        compressionConfig: CompressionConfig,
    ): CompressionResult {
        return try {
            val compressedImageFile =
                generateFile(
                    path = context.cacheDir.absolutePath,
                    fileName = fileName,
                ) ?: return CompressionResult.Error("Could not reach destination directory")

            val compressedImageByteArray = resizeImageAndCompress(fileUri, compressionConfig)

            val isByteArraySuccessfullyWritten =
                compressedImageFile.writeByteArray(
                    byteArray = compressedImageByteArray,
                )

            if (isByteArraySuccessfullyWritten) {
                context.contentResolver.openInputStream(fileUri)?.use {
                    restoreExifInfoAfterCompression(it, compressedImageFile)
                }
            }

            if (isByteArraySuccessfullyWritten) {
                CompressionResult.Success(
                    imageState = CompressionResult.CompressionState.COMPRESSED,
                    imageName = fileName,
                    imageUri = Uri.fromFile(compressedImageFile),
                )
            } else {
                CompressionResult.Error("Could not write compressed image into file")
            }
        } catch (e: Exception) {
            CompressionResult.Error("Failed to compress image - $e")
        }
    }

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

    private fun resizeImageAndCompress(
        imageUri: Uri,
        compressionConfig: CompressionConfig,
    ): ByteArray {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(BufferedInputStream(inputStream))

        val resizedBitmap =
            if (bitmap.width > compressionConfig.compressedImageMaxSize ||
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

    /**
     * Compression of a Bitmap stirs up all exif meta-data and we need to restore it
     */
    private fun restoreExifInfoAfterCompression(
        imageUriInputStream: InputStream,
        compressedImageFile: File,
    ) {
        val originalImageExif = ExifInterface(imageUriInputStream)
        val compressedImageExif = ExifInterface(compressedImageFile)

        with(originalImageExif.getAttribute(ExifInterface.TAG_ORIENTATION)) {
            if (this == null) {
                return@with
            }

            compressedImageExif.setAttribute(ExifInterface.TAG_ORIENTATION, this)
            compressedImageExif.saveAttributes()
        }
    }
}
