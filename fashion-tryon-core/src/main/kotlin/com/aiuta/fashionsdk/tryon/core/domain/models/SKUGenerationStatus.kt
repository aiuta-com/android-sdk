package com.aiuta.fashionsdk.tryon.core.domain.models

/**
 * Status sku generation
 */
public sealed interface SKUGenerationStatus {
    @Deprecated("Don't use it")
    public val imageUrls: List<String>

    /**
     * Base type, which mean nothing to see
     */
    @Deprecated("Delete")
    public object NothingGenerateStatus : SKUGenerationStatus {
        override val imageUrls: List<String> = emptyList()
    }

    /**
     * Successfully generate all images
     */
    public class SuccessGenerationStatus(
        public val sourceImageUrl: String,
        public override val imageUrls: List<String> = emptyList(), // TODO Delete override
    ) : SKUGenerationStatus

    /**
     * Generation is in progress
     */
    public interface LoadingGenerationStatus : SKUGenerationStatus {
        /**
         * Only start generation process
         */
        public object StartGeneration : LoadingGenerationStatus {
            override val imageUrls: List<String> = emptyList()
        }

        /**
         * Source image successfully upload to storage
         */
        public class UploadedSourceImage(
            public val sourceImageUrl: String,
            override val imageUrls: List<String> = emptyList(),
        ) : LoadingGenerationStatus

        /**
         * Generation operation successfully created and now should wait until
         * finish of generation
         */
        public class GenerationProcessing(
            public val sourceImageUrl: String,
            override val imageUrls: List<String> = emptyList(),
        ) : LoadingGenerationStatus
    }

    /**
     * Failed to make generation
     */
    public class ErrorGenerationStatus(
        public val errorMessage: String? = null,
        public val exception: Exception? = null,
        override val imageUrls: List<String> = emptyList(),
    ) : SKUGenerationStatus
}
