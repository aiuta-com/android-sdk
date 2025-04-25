package com.aiuta.fashionsdk.configuration.features.picker

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.picker.camera.AiutaImagePickerCameraFeature
import com.aiuta.fashionsdk.configuration.features.picker.gallery.AiutaImagePickerPhotoGalleryFeature
import com.aiuta.fashionsdk.configuration.features.picker.history.AiutaImagePickerUploadsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.picker.images.AiutaImagePickerFeatureImages
import com.aiuta.fashionsdk.configuration.features.picker.model.AiutaImagePickerPredefinedModelFeature
import com.aiuta.fashionsdk.configuration.features.picker.strings.AiutaImagePickerFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaImagePickerFeature private constructor(
    // Features
    public val camera: AiutaImagePickerCameraFeature?,
    public val photoGallery: AiutaImagePickerPhotoGalleryFeature,
    public val predefinedModels: AiutaImagePickerPredefinedModelFeature?,
    public val uploadsHistory: AiutaImagePickerUploadsHistoryFeature?,
    // General
    public val images: AiutaImagePickerFeatureImages,
    public val strings: AiutaImagePickerFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var camera: AiutaImagePickerCameraFeature? = null
        public var photoGallery: AiutaImagePickerPhotoGalleryFeature? = null
        public var predefinedModels: AiutaImagePickerPredefinedModelFeature? = null
        public var uploadsHistory: AiutaImagePickerUploadsHistoryFeature? = null
        public var images: AiutaImagePickerFeatureImages? = null
        public var strings: AiutaImagePickerFeatureStrings? = null

        public override fun build(): AiutaImagePickerFeature {
            val parentClass = "AiutaImagePickerFeature"

            return AiutaImagePickerFeature(
                camera = camera,
                photoGallery = photoGallery.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "photoGallery",
                ),
                predefinedModels = predefinedModels,
                uploadsHistory = uploadsHistory,
                images = images.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "images",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

public inline fun AiutaFeatures.Builder.imagePicker(
    block: AiutaImagePickerFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    imagePicker = AiutaImagePickerFeature.Builder().apply(block).build()
}
