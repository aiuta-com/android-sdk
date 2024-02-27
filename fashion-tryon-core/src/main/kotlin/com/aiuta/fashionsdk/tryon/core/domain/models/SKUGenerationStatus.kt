package com.aiuta.fashionsdk.tryon.core.domain.models

/**
 * Status sku generation
 */
public sealed interface SKUGenerationStatus {
    public val imageUrls: List<String>

    /**
     * Base type, which mean nothing to see
     */
    public object NothingGenerateStatus : SKUGenerationStatus {
        override val imageUrls: List<String> = emptyList()
    }

    /**
     * Successfully generate all images
     */
    public data class SuccessGenerationStatus(
        override val imageUrls: List<String> = emptyList(),
    ) : SKUGenerationStatus

    /**
     * Generation is in progress
     */
    public data class LoadingGenerationStatus(
        override val imageUrls: List<String> = emptyList(),
    ) : SKUGenerationStatus

    /**
     * Failed to make generation
     */
    public data class ErrorGenerationStatus(
        override val imageUrls: List<String> = emptyList(),
        val errorMessage: String? = null,
        val exception: Exception? = null,
    ) : SKUGenerationStatus
}
