package com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaInputImage
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for custom uploads history data provider implementation.
 *
 * This interface allows for custom implementation of uploads history management,
 * providing methods to observe and modify the list of uploaded images.
 */
public interface AiutaImagePickerUploadsHistoryFeatureDataProviderCustom : AiutaImagePickerUploadsHistoryFeatureDataProvider {
    /**
     * A [StateFlow] containing the list of currently uploaded images.
     * This flow can be observed to react to changes in the uploads history.
     */
    public val uploadedImages: StateFlow<List<AiutaInputImage>>

    /**
     * Adds new images to the uploads history.
     *
     * @param images List of images to add to the history
     */
    public suspend fun addUploadedImages(images: List<AiutaInputImage>)

    /**
     * Removes images from the uploads history.
     *
     * @param images List of images to remove from the history
     */
    public suspend fun deleteUploadedImages(images: List<AiutaInputImage>)

    /**
     * Marks an image as selected in the uploads history.
     *
     * @param image The image to select
     */
    public suspend fun selectUploadedImage(image: AiutaInputImage)
}
