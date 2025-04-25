package com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.images.AiutaHistoryImage
import kotlinx.coroutines.flow.StateFlow

public interface AiutaImagePickerUploadsHistoryFeatureDataProvider {
    public val uploadedImages: StateFlow<List<AiutaHistoryImage>>
    public fun addUploadedImages(images: List<AiutaHistoryImage>)
    public fun deleteUploadedImages(images: List<AiutaHistoryImage>)
    public fun selectUploadedImage(image: AiutaHistoryImage)
}
