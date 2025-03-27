package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toUiModel
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.meta.AiutaTryOnMetadata

@Immutable
internal sealed interface SKUGenerationOperation {
    val operationId: String

    sealed interface LoadingOperation : SKUGenerationOperation {
        class StartGenerationOperation(
            override val operationId: String,
        ) : LoadingOperation

        class UploadedSourceImageOperation(
            override val operationId: String,
        ) : LoadingOperation

        class GenerationProcessingOperation(
            override val operationId: String,
        ) : LoadingOperation
    }

    class SuccessOperation(
        override val operationId: String,
        val uploadedSourceImageId: String,
        val uploadedSourceImage: String,
        val generatedImages: List<GeneratedImageUIModel>,
        val metadata: AiutaTryOnMetadata,
    ) : SKUGenerationOperation

    class ErrorOperation(
        override val operationId: String,
        val errorMessage: String? = null,
        val exception: Exception? = null,
    ) : SKUGenerationOperation
}

internal fun SKUGenerationStatus.toOperation(): SKUGenerationOperation {
    return when (this) {
        is SKUGenerationStatus.LoadingGenerationStatus.StartGeneration ->
            SKUGenerationOperation.LoadingOperation.StartGenerationOperation(
                operationId = statusId,
            )

        is SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage ->
            SKUGenerationOperation.LoadingOperation.UploadedSourceImageOperation(
                operationId = statusId,
            )

        is SKUGenerationStatus.LoadingGenerationStatus.GenerationProcessing ->
            SKUGenerationOperation.LoadingOperation.GenerationProcessingOperation(
                operationId = statusId,
            )

        is SKUGenerationStatus.ErrorGenerationStatus ->
            SKUGenerationOperation.ErrorOperation(
                operationId = statusId,
                errorMessage = errorMessage,
                exception = exception,
            )

        is SKUGenerationStatus.SuccessGenerationStatus ->
            SKUGenerationOperation.SuccessOperation(
                operationId = statusId,
                uploadedSourceImageId = sourceImageId,
                uploadedSourceImage = sourceImageUrl,
                generatedImages = images.map { it.toUiModel() },
                metadata = metadata,
            )
    }
}
