package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.domain.models.meta.AiutaTryOnMetadata
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Status sku generation
 */
public sealed interface SKUGenerationStatus {
    public val statusId: String

    /**
     * Successfully generate all images
     */
    public class SuccessGenerationStatus(
        override val statusId: String,
        public val sourceImageId: String,
        public val sourceImageUrl: String,
        public val images: List<SKUGeneratedImage> = emptyList(),
        public val metadata: AiutaTryOnMetadata,
    ) : SKUGenerationStatus

    /**
     * Generation is in progress
     */
    public sealed interface LoadingGenerationStatus : SKUGenerationStatus {
        /**
         * Only start generation process
         */
        public class StartGeneration(override val statusId: String) : LoadingGenerationStatus

        /**
         * Source image successfully upload to storage
         */
        public class UploadedSourceImage(
            override val statusId: String,
            public val sourceImageId: String,
            public val sourceImageUrl: String,
        ) : LoadingGenerationStatus

        /**
         * Generation operation successfully created and now should wait until
         * finish of generation
         */
        public class GenerationProcessing(
            override val statusId: String,
            public val sourceImageId: String,
            public val sourceImageUrl: String,
        ) : LoadingGenerationStatus
    }

    /**
     * Failed to make generation
     */
    public class ErrorGenerationStatus(
        override val statusId: String,
        public val errorMessage: String? = null,
        public val exception: Exception? = null,
    ) : SKUGenerationStatus
}

@OptIn(ExperimentalUuidApi::class)
internal fun generateStatusId(): String = "generation-status-${Uuid.random().toHexString()}"
