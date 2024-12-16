package com.aiuta.fashionsdk.tryon.core.domain.slice.ping

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.GeneratedImage

/**
 * Slice responsible for checking current status of operation
 */
internal interface PingOperationSlice {
    /**
     * Start new operation by [operationId]
     */
    suspend fun operationPing(operationId: String): List<GeneratedImage>
}
