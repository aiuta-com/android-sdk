package com.aiuta.fashionsdk.configuration.features.picker.gallery

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.gallery.icons.AiutaImagePickerPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.gallery.strings.AiutaImagePickerPhotoGalleryFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the photo gallery feature in the image picker.
 *
 * This feature provides functionality for selecting photos from the device's gallery,
 * including icons for the UI and text strings for localization.
 *
 * Required components:
 * - [icons]: Icons used in the gallery interface
 * - [strings]: Text strings for the gallery interface
 */
public class AiutaImagePickerPhotoGalleryFeature(
    public val icons: AiutaImagePickerPhotoGalleryFeatureIcons,
    public val strings: AiutaImagePickerPhotoGalleryFeatureStrings,
) : AiutaFeature {

    /**
     * Builder class for creating [AiutaImagePickerPhotoGalleryFeature] instances.
     *
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
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

/**
 * DSL function for configuring the photo gallery feature.
 *
 * Example usage:
 * ```
 * imagePicker {
 *     photoGallery {
 *         icons = ...
 *         strings = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaImagePickerFeature.Builder.photoGallery(
    block: AiutaImagePickerPhotoGalleryFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    photoGallery = AiutaImagePickerPhotoGalleryFeature.Builder().apply(block).build()
}
