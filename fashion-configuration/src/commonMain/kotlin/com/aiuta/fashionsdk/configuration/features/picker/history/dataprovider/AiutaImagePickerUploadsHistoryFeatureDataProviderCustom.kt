package com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaImagePickerUploadsHistoryFeatureDataProviderCustom : AiutaImagePickerUploadsHistoryFeatureDataProvider {
    public val uploadedImages: StateFlow<List<AiutaHistoryImage>>
    public suspend fun addUploadedImages(images: List<AiutaHistoryImage>)
    public suspend fun deleteUploadedImages(images: List<AiutaHistoryImage>)
    public suspend fun selectUploadedImage(image: AiutaHistoryImage)
}
