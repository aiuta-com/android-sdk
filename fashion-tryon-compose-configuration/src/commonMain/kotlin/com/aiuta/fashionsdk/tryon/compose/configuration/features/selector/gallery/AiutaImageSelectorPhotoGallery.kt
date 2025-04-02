package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.icons.AiutaImageSelectorPhotoGalleryIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.strings.AiutaImageSelectorPhotoGalleryStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorPhotoGallery private constructor(
    public val icons: AiutaImageSelectorPhotoGalleryIcons,
    public val strings: AiutaImageSelectorPhotoGalleryStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImageSelectorPhotoGalleryIcons? = null
        public var strings: AiutaImageSelectorPhotoGalleryStrings? = null

        public override fun build(): AiutaImageSelectorPhotoGallery {
            val parentClass = "AiutaImageSelectorPhotoGallery"

            return AiutaImageSelectorPhotoGallery(
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
    block: AiutaImageSelectorPhotoGallery.Builder.() -> Unit,
) {
    photoGallery = AiutaImageSelectorPhotoGallery.Builder().apply(block).build()
}
