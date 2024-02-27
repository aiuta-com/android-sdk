package com.aiuta.fashionsdk.tryon.core.domain.slice.upload.internal

import android.content.Context
import android.net.Uri
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.FashionImageDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.domain.compressor.ImageCompressor
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionConfig
import com.aiuta.fashionsdk.tryon.core.domain.compressor.models.CompressionResult
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException
import com.aiuta.fashionsdk.tryon.core.utils.readBytes

internal class UploadImageSliceImpl(
    private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val imageDataSource: FashionImageDataSource,
) : UploadImageSlice {
    override suspend fun uploadImage(
        fileUri: Uri,
        fileName: String,
    ): UploadedImage {
        // Try to compress image or use origin uri
        val compressedImageUri =
            compressImage(
                fileUri = fileUri,
                fileName = fileName,
            ) ?: fileUri

        // Open byte array from Uri
        val byteArray =
            readBytes(
                context = context,
                uri = compressedImageUri,
            )

        // Upload image to backend
        return byteArray?.let {
            imageDataSource.createUploadedImage(
                fileName = fileName,
                fileByteArray = byteArray,
            )
        } ?: throw FashionReadBytesException()
    }

    private suspend fun compressImage(
        fileUri: Uri,
        fileName: String,
    ): Uri? {
        val compressedImageResult =
            imageCompressor.compressImageAndResize(
                fileName = fileName,
                fileUri = fileUri,
                compressionConfig = CompressionConfig.DEFAULT,
            )

        return if (compressedImageResult is CompressionResult.Success) {
            compressedImageResult.imageUri
        } else {
            null
        }
    }
}
