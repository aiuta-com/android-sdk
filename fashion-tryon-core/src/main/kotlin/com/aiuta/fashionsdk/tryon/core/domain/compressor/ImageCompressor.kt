package com.aiuta.fashionsdk.tryon.core.domain.compressor

import android.net.Uri
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionConfig
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionResult

/**
 * [ImageCompressor] - SAM interface for wrapping implementation of image compression
 *
 * This unit heavily relies on idea that we get Uri and return Uri. This decision comes from
 * desire to store less data on RAM, so data is pushed on External Storage
 * and then its Uri retrieved
 */
internal interface ImageCompressor {
    /**
     * [compressImageAndResize] is used for compressing image represented by [fileUri]
     *
     * @param fileName Name of image file
     * @param fileUri Uri of image
     *
     * @return wrapper for image compression result that can indicate an error during compression
     * or success result
     */
    public suspend fun compressImageAndResize(
        fileName: String,
        fileUri: Uri,
        compressionConfig: CompressionConfig,
    ): CompressionResult
}
