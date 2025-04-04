package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.AiutaImageSelectorCameraFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.AiutaImageSelectorPhotoGalleryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.images.AiutaImageSelectorFeatureImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModelFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings.AiutaImageSelectorFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription

@Immutable
public class AiutaImageSelectorFeature private constructor(
    // Features
    public val camera: AiutaImageSelectorCameraFeature?,
    public val photoGallery: AiutaImageSelectorPhotoGalleryFeature,
    public val predefinedModels: AiutaImageSelectorPredefinedModelFeature?,
    public val uploadsHistory: AiutaImageSelectorUploadsHistoryFeature?,
    // General
    public val images: AiutaImageSelectorFeatureImages,
    public val strings: AiutaImageSelectorFeatureStrings,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var camera: AiutaImageSelectorCameraFeature? = null
        public var photoGallery: AiutaImageSelectorPhotoGalleryFeature? = null
        public var predefinedModels: AiutaImageSelectorPredefinedModelFeature? = null
        public var uploadsHistory: AiutaImageSelectorUploadsHistoryFeature? = null
        public var images: AiutaImageSelectorFeatureImages? = null
        public var strings: AiutaImageSelectorFeatureStrings? = null

        public override fun build(): AiutaImageSelectorFeature {
            val parentClass = "AiutaImageSelectorFeature"

            return AiutaImageSelectorFeature(
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

public inline fun AiutaTryOnConfigurationFeatures.Builder.imageSelector(
    block: AiutaImageSelectorFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    imageSelector = AiutaImageSelectorFeature.Builder().apply(block).build()
}
