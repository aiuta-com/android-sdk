package com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaInputImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaImagePickerUploadsHistoryFeatureDataProviderCustom : AiutaImagePickerUploadsHistoryFeatureDataProvider {
    public val uploadedImages: StateFlow<List<AiutaInputImage>>
    public suspend fun addUploadedImages(images: List<AiutaInputImage>)
    public suspend fun deleteUploadedImages(images: List<AiutaInputImage>)
    public suspend fun selectUploadedImage(image: AiutaInputImage)
}
