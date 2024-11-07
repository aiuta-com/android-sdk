package com.aiuta.fashionsdk.tryon.core.domain.models

/**
 * Status sku generation
 */
public sealed interface SKUGenerationStatus {
    /**
     * Successfully generate all images
     */
    public class SuccessGenerationStatus(
        public val sourceImageUrl: String,
        public val images: List<SKUGeneratedImage> = emptyList(),
    ) : SKUGenerationStatus

    /**
     * Generation is in progress
     */
    public interface LoadingGenerationStatus : SKUGenerationStatus {
        /**
         * Only start generation process
         */
        public object StartGeneration : LoadingGenerationStatus

        /**
         * Source image successfully upload to storage
         */
        public class UploadedSourceImage(
            public val sourceImageId: String,
            public val sourceImageUrl: String,
        ) : LoadingGenerationStatus

        /**
         * Generation operation successfully created and now should wait until
         * finish of generation
         */
        public class GenerationProcessing(
            public val sourceImageId: String,
            public val sourceImageUrl: String,
        ) : LoadingGenerationStatus
    }

    /**
     * Failed to make generation
     */
    public class ErrorGenerationStatus(
        public val errorMessage: String? = null,
        public val exception: Exception? = null,
    ) : SKUGenerationStatus
}
