package com.aiuta.fashionsdk.tryon.core.domain.models

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
        val imageUrls: List<String> = emptyList(),
    ) : PingGenerationStatus

    /**
     * Generation is in progress
     */
    public data class LoadingPingGenerationStatus(
        val imageUrls: List<String> = emptyList(),
    ) : PingGenerationStatus

    /**
     * Failed to make generation
     */
    public data class ErrorPingGenerationStatus(
        val imageUrls: List<String> = emptyList(),
        val errorMessage: String? = null,
        val exception: Exception? = null,
    ) : PingGenerationStatus
}

internal fun PingGenerationStatus.isTerminate(): Boolean {
    return this is PingGenerationStatus.SuccessPingGenerationStatus ||
        this is PingGenerationStatus.ErrorPingGenerationStatus
}
