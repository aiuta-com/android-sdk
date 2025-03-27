package com.aiuta.fashionsdk.tryon.core.domain.slice.upload.internal

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.FashionImageDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendInternalErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationPlatformImageContainer
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException

internal class UploadImageSliceImpl(
    private val analytic: InternalAiutaAnalytic,
    private val imageDataSource: FashionImageDataSource,
) : UploadImageSlice {
    override suspend fun uploadImage(
        container: SKUGenerationPlatformImageContainer,
        fileName: String,
    ): UploadedImage {
        // Compress, resize and transform to byte array
        val byteArray = container.aiutaPlatformImage.byteArray

        // Upload image to backend
        return try {
            imageDataSource.createUploadedImage(
                fileName = fileName,
                fileByteArray = byteArray,
            )
        } catch (e: Exception) {
            analytic.sendInternalErrorEvent(
                container = container,
                type = AiutaTryOnExceptionType.PREPARE_PHOTO_FAILED,
            )
            throw FashionReadBytesException()
        }
    }
}
