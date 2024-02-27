package com.aiuta.fashionsdk.tryon.core.domain.compressor.models

import android.net.Uri

/**
 * [CompressionResult] represents result of image compression
 */
internal sealed interface CompressionResult {
    enum class CompressionState {
        /**
         * [CompressionState.COMPRESSED] represents successfully compressed image result
         *
         * In this case, [CompressionResult.Success.imageName] is the name of the image
         * that was compressed
         * In this case, [CompressionResult.Success.imageUri] is the uri of compressed image
         * (not the image that was passed for compression)
         */
        COMPRESSED,

        /**
         * [CompressionState.INITIAL] represents not compressed image result
         *
         * In this case, [CompressionResult.Success.imageName] is the name of the image
         * that was compressed
         * In this case, [CompressionResult.Success.imageUri] is the uri of the image
         * that should have been compressed but was not (uri should be the same as the one
         * that was passed for compression)
         */
        INITIAL,
    }

    /**
     * [CompressionResult.Success] represents successfully compressed image result
     *
     * @param imageState - state of image compression performed
     * @param imageName - name of image that was compressed
     * @param imageUri - uri of compressed image (not the image that was passed for compression)
     */
    class Success(
        val imageState: CompressionState,
        val imageName: String,
        val imageUri: Uri,
    ) : CompressionResult

    /**
     * [CompressionResult.Error] represents error that happened during compression
     *
     * @param message - message of error that happened during image compression
     */
    class Error(
        val message: String?,
    ) : CompressionResult
}
