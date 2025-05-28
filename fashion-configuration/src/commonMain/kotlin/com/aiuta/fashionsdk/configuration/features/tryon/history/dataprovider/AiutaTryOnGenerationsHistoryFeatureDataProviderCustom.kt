package com.aiuta.fashionsdk.configuration.features.tryon.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaGeneratedImage
import kotlinx.coroutines.flow.StateFlow

/**
 * Custom implementation of [AiutaTryOnGenerationsHistoryFeatureDataProvider].
 *
 * This interface allows for custom implementation of history data management,
 * providing methods to manage generated images and their associated products.
 *
 * @property generatedImages Flow of currently stored generated images
 */
public interface AiutaTryOnGenerationsHistoryFeatureDataProviderCustom : AiutaTryOnGenerationsHistoryFeatureDataProvider {
    public val generatedImages: StateFlow<List<AiutaGeneratedImage>>

    /**
     * Adds newly generated images to the history.
     *
     * @param productIds List of product identifiers associated with the generated images
     * @param images List of generated images to be added to history
     */
    public suspend fun addGeneratedImages(productIds: List<String>, images: List<AiutaGeneratedImage>)

    /**
     * Removes specified images from the history.
     *
     * @param images List of generated images to be removed from history
     */
    public suspend fun deleteGeneratedImages(images: List<AiutaGeneratedImage>)
}
