package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.GeneratedImage

/**
 * Current status of ping operation
 */
internal sealed interface PingGenerationStatus {
    /**
     * Base type, which mean nothing to see
     */
    public object NothingGenerateStatus : PingGenerationStatus

    /**
     * Starting of generation photos, and ping until end
     */
    public object StartPingGenerationStatus : PingGenerationStatus

    /**
     * Successfully generate all images
     */
    public data class SuccessPingGenerationStatus(
        val images: List<GeneratedImage> = emptyList(),
    ) : PingGenerationStatus

    /**
     * Generation is in progress
     */
    public data class LoadingPingGenerationStatus(
        val images: List<GeneratedImage> = emptyList(),
    ) : PingGenerationStatus

    /**
     * Failed to make generation
     */
    public data class ErrorPingGenerationStatus(
        val images: List<GeneratedImage> = emptyList(),
        val errorMessage: String? = null,
        val exception: Exception? = null,
    ) : PingGenerationStatus
}

internal fun PingGenerationStatus.isTerminate(): Boolean {
    return this is PingGenerationStatus.SuccessPingGenerationStatus ||
        this is PingGenerationStatus.ErrorPingGenerationStatus
}
