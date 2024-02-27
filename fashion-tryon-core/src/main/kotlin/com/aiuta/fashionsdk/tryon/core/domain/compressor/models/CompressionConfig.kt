package com.aiuta.fashionsdk.tryon.core.domain.compressor.models

/**
 * [CompressionConfig] represents params of image compression
 *
 * @param compressedImageMaxSize - max size of an image after its resizing and compression
 * @param compressedImageQuality - value of quality that we want to have after image compression
 */
internal class CompressionConfig(
    val compressedImageMaxSize: Int,
    val compressedImageQuality: Int,
) {
    init {
        require(compressedImageQuality in 0..100) {
            ERROR_QUALITY_EXCEPTION
        }
    }

    companion object {
        /**
         * These are temp default values for image compression
         */
        private const val COMPRESSED_IMAGE_MAX_SIZE = 1500
        private const val COMPRESSED_IMAGE_QUALITY = 90

        private const val ERROR_QUALITY_EXCEPTION = """
            CompressionConfig: New image quality should be set from 0 to 100 percent
        """

        val DEFAULT by lazy {
            CompressionConfig(
                compressedImageMaxSize = COMPRESSED_IMAGE_MAX_SIZE,
                compressedImageQuality = COMPRESSED_IMAGE_QUALITY,
            )
        }
    }
}
