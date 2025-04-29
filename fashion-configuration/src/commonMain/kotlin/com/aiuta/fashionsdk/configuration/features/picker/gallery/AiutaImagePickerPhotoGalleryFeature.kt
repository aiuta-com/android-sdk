package com.aiuta.fashionsdk.configuration.features.picker.gallery

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.gallery.icons.AiutaImagePickerPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.gallery.strings.AiutaImagePickerPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImagePickerPhotoGalleryFeature(
    public val icons: AiutaImagePickerPhotoGalleryFeatureIcons,
    public val strings: AiutaImagePickerPhotoGalleryFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImagePickerPhotoGalleryFeatureIcons? = null
        public var strings: AiutaImagePickerPhotoGalleryFeatureStrings? = null

        public override fun build(): AiutaImagePickerPhotoGalleryFeature {
            val parentClass = "AiutaImagePickerPhotoGalleryFeature"

            return AiutaImagePickerPhotoGalleryFeature(
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

public inline fun AiutaImagePickerFeature.Builder.photoGallery(
    block: AiutaImagePickerPhotoGalleryFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    photoGallery = AiutaImagePickerPhotoGalleryFeature.Builder().apply(block).build()
}
