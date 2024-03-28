package com.aiuta.fashionsdk.tryon.compose.domain.models

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

@Immutable
internal sealed interface SKUGenerationOperation {
    val sourceImageUri: Uri

    class LoadingOperation(
        override val sourceImageUri: Uri,
    ) : SKUGenerationOperation

    class SuccessOperation(
        override val sourceImageUri: Uri,
        val generatedImageUrls: List<String>,
    ) : SKUGenerationOperation

    class ErrorOperation(
        override val sourceImageUri: Uri,
    ) : SKUGenerationOperation
}

internal fun SKUGenerationStatus.toOperation(sourceUri: Uri): SKUGenerationOperation {
    return when (this) {
        is SKUGenerationStatus.LoadingGenerationStatus ->
            SKUGenerationOperation.LoadingOperation(
                sourceImageUri = sourceUri,
            )

        is SKUGenerationStatus.ErrorGenerationStatus ->
            SKUGenerationOperation.ErrorOperation(
                sourceImageUri = sourceUri,
            )

        is SKUGenerationStatus.SuccessGenerationStatus ->
            SKUGenerationOperation.SuccessOperation(
                sourceImageUri = sourceUri,
                generatedImageUrls = imageUrls,
            )

        // TODO Delete
        else ->
            SKUGenerationOperation.LoadingOperation(
                sourceImageUri = sourceUri,
            )
    }
}
