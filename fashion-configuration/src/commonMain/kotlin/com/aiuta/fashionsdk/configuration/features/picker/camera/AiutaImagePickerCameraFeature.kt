package com.aiuta.fashionsdk.configuration.features.picker.camera

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.camera.icons.AiutaImagePickerCameraFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.camera.strings.AiutaImagePickerCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the camera feature in the image picker.
 * 
 * This feature provides functionality for capturing photos using the device's camera,
 * including icons for the UI and text strings for localization.
 * 
 * Required components:
 * - [icons]: Icons used in the camera interface
 * - [strings]: Text strings for the camera interface
 */
public class AiutaImagePickerCameraFeature(
    public val icons: AiutaImagePickerCameraFeatureIcons,
    public val strings: AiutaImagePickerCameraFeatureStrings,
) : AiutaFeature {

    /**
     * Builder class for creating [AiutaImagePickerCameraFeature] instances.
     * 
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImagePickerCameraFeatureIcons? = null
        public var strings: AiutaImagePickerCameraFeatureStrings? = null

        public override fun build(): AiutaImagePickerCameraFeature {
            val parentClass = "AiutaImagePickerCameraFeature"

            return AiutaImagePickerCameraFeature(
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
 * DSL function for configuring the camera feature.
 * 
 * Example usage:
 * ```
 * imagePicker {
 *     camera {
 *         icons = ...
 *         strings = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaImagePickerFeature.Builder.camera(
    block: AiutaImagePickerCameraFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    camera = AiutaImagePickerCameraFeature.Builder().apply(block).build()
}
