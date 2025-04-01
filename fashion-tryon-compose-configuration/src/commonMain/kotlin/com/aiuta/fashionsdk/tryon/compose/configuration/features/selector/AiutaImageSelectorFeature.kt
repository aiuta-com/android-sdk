package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.AiutaImageSelectorCamera
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.AiutaImageSelectorPhotoGallery
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistory
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.images.AiutaImageSelectorFeatureImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModel
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.strings.AiutaImageSelectorFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorFeature private constructor(
    // Features
    public val camera: AiutaImageSelectorCamera?,
    public val photoGallery: AiutaImageSelectorPhotoGallery,
    public val predefinedModels: AiutaImageSelectorPredefinedModel?,
    public val uploadsHistory: AiutaImageSelectorUploadsHistory?,
    // General
    public val images: AiutaImageSelectorFeatureImages,
    public val strings: AiutaImageSelectorFeatureStrings,
) {
    @AiutaDsl
    public class Builder {
        public var camera: AiutaImageSelectorCamera? = null
        public var photoGallery: AiutaImageSelectorPhotoGallery? = null
        public var predefinedModels: AiutaImageSelectorPredefinedModel? = null
        public var uploadsHistory: AiutaImageSelectorUploadsHistory? = null
        public var images: AiutaImageSelectorFeatureImages? = null
        public var strings: AiutaImageSelectorFeatureStrings? = null

        public fun build(): AiutaImageSelectorFeature {
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

public inline fun AiutaTryOnFeatures.Builder.imageSelector(
    block: AiutaImageSelectorFeature.Builder.() -> Unit,
) {
    imageSelector = AiutaImageSelectorFeature.Builder().apply(block).build()
}
