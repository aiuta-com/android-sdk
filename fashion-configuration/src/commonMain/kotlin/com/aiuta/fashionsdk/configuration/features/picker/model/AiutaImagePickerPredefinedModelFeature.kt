package com.aiuta.fashionsdk.configuration.features.picker.model

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.model.data.AiutaImagePickerPredefinedModelFeatureData
import com.aiuta.fashionsdk.configuration.features.picker.model.icons.AiutaImagePickerPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.model.strings.AiutaImagePickerPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for predefined model images in the image picker feature.
 *
 * This feature allows users to select from a set of predefined model images
 * instead of using their own photos. It includes data for available models,
 * icons for the UI, and text strings for localization.
 *
 * Required components:
 * - [icons]: Icons used in the predefined models UI
 * - [strings]: Text strings for the predefined models interface
 *
 * Optional components:
 * - [data]: Data provider for predefined model images
 */
public class AiutaImagePickerPredefinedModelFeature(
    public val data: AiutaImagePickerPredefinedModelFeatureData?,
    public val icons: AiutaImagePickerPredefinedModelFeatureIcons,
    public val strings: AiutaImagePickerPredefinedModelFeatureStrings,
) : AiutaFeature {
    /**
     * Builder class for creating [AiutaImagePickerPredefinedModelFeature] instances.
     *
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var data: AiutaImagePickerPredefinedModelFeatureData? = null
        public var icons: AiutaImagePickerPredefinedModelFeatureIcons? = null
        public var strings: AiutaImagePickerPredefinedModelFeatureStrings? = null

        public override fun build(): AiutaImagePickerPredefinedModelFeature {
            val parentClass = "AiutaImagePickerPredefinedModelFeature"

            return AiutaImagePickerPredefinedModelFeature(
                data = data,
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
 * DSL function for configuring the predefined models feature.
 *
 * Example usage:
 * ```
 * imagePicker {
 *     predefinedModels {
 *         data = ...
 *         icons = ...
 *         strings = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaImagePickerFeature.Builder.predefinedModels(
    block: AiutaImagePickerPredefinedModelFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    predefinedModels = AiutaImagePickerPredefinedModelFeature.Builder().apply(block).build()
}
