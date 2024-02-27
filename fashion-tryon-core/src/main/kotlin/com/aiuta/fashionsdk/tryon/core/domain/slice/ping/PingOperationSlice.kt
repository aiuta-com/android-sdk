package com.aiuta.fashionsdk.tryon.core.domain.slice.ping

import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import kotlinx.coroutines.flow.StateFlow

/**
 * Slice responsible for checking current status of operation
 */
internal interface PingOperationSlice {
    /**
     * Get flow of [PingGenerationStatus] by provided [operationId]
     */
    fun getPingGenerationStatusFlow(operationId: String): StateFlow<PingGenerationStatus>?

    /**
     * Start new operation by [operationId]
     *
     * @throws IllegalStateException if such operation already exist
     */
    fun startOperationTypeListening(operationId: String)

    /**
     * Cancel operation by [operationId] if it exist
     */
    fun cancelOperation(operationId: String)
}
