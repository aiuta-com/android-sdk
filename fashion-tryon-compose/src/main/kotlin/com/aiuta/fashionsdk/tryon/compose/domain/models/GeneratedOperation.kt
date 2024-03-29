package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationWithImages

@Immutable
internal class GeneratedOperation(
    val operationId: Long,
    val sourceImageUrls: List<String>,
)

internal fun GeneratedOperationWithImages.toUiModel(): GeneratedOperation {
    return GeneratedOperation(
        operationId = operation.id,
        sourceImageUrls = sourceImages.map { it.imageUrl },
    )
}
