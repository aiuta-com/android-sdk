package com.aiuta.fashionsdk.io.compression

/**
 * [AiutaCompressionConfig] represents params of image compression
 *
 * @param compressedImageMaxSize - max size of an image after its resizing and compression
 * @param compressedImageQuality - value of quality that we want to have after image compression
 */
public class AiutaCompressionConfig(
    public val compressedImageMaxSize: Int,
    public val compressedImageQuality: Int,
) {
    init {
        require(compressedImageQuality in 0..100) {
            ERROR_QUALITY_EXCEPTION
        }
    }

    public companion object {
        /**
         * These are temp default values for image compression
         */
        private const val COMPRESSED_IMAGE_MAX_SIZE = 1500
        private const val COMPRESSED_IMAGE_QUALITY = 65

        private const val ERROR_QUALITY_EXCEPTION = """
            AiutaCompressionConfig: New image quality should be set from 0 to 100 percent
        """

        public val DEFAULT: AiutaCompressionConfig by lazy {
            AiutaCompressionConfig(
                compressedImageMaxSize = COMPRESSED_IMAGE_MAX_SIZE,
                compressedImageQuality = COMPRESSED_IMAGE_QUALITY,
            )
        }
    }
}
