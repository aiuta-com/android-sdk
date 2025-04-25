package com.aiuta.fashionsdk.configuration.features.features.selector.gallery

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.configuration.features.features.selector.gallery.icons.AiutaImageSelectorPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.configuration.features.features.selector.gallery.strings.AiutaImageSelectorPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImageSelectorPhotoGalleryFeature private constructor(
    public val icons: AiutaImageSelectorPhotoGalleryFeatureIcons,
    public val strings: AiutaImageSelectorPhotoGalleryFeatureStrings,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaImageSelectorPhotoGalleryFeatureIcons? = null
        public var strings: AiutaImageSelectorPhotoGalleryFeatureStrings? = null

        public override fun build(): AiutaImageSelectorPhotoGalleryFeature {
            val parentClass = "AiutaImageSelectorPhotoGalleryFeature"

            return AiutaImageSelectorPhotoGalleryFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

public inline fun AiutaImageSelectorFeature.Builder.photoGallery(
    block: AiutaImageSelectorPhotoGalleryFeature.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    photoGallery = AiutaImageSelectorPhotoGalleryFeature.Builder().apply(block).build()
}
