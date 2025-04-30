package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import com.aiuta.fashionsdk.tryon.core.domain.models.meta.AiutaTryOnMetadata
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Status product generation
 */
public sealed interface ProductGenerationStatus {
    public val statusId: String

    /**
     * Successfully generate all images
     */
    public class SuccessGenerationStatus(
        override val statusId: String,
        public val sourceImageId: String,
        public val sourceImageUrl: String,
        public val sourceImageType: AiutaFileType,
        public val images: List<ProductGeneratedImage> = emptyList(),
        public val metadata: AiutaTryOnMetadata,
    ) : ProductGenerationStatus

    /**
     * Generation is in progress
     */
    public sealed interface LoadingGenerationStatus : ProductGenerationStatus {
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
    ) : ProductGenerationStatus
}

@OptIn(ExperimentalUuidApi::class)
internal fun generateStatusId(): String = "generation-status-${Uuid.random().toHexString()}"
