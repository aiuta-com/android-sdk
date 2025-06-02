package com.aiuta.fashionsdk.tryon.core.domain.slice.upload.internal

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.io.readCompressedByteArray
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.FashionImageDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationPlatformImageContainer
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException

internal class UploadImageSliceImpl(
    private val context: AiutaPlatformContext,
    private val imageDataSource: FashionImageDataSource,
) : UploadImageSlice {
    override suspend fun uploadImage(
        container: ProductGenerationPlatformImageContainer,
        fileName: String,
    ): UploadedImage {
        // Compress, resize and transform to byte array
        val byteArray = container.platformFile.readCompressedByteArray(context)

        // Upload image to backend
        return try {
            imageDataSource.createUploadedImage(
                fileName = fileName,
                fileByteArray = byteArray,
            )
        } catch (e: Exception) {
            throw FashionReadBytesException()
        }
    }
}
