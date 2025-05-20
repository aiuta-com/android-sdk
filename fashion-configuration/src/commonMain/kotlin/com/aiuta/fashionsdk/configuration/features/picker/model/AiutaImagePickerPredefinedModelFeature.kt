package com.aiuta.fashionsdk.configuration.features.picker.model

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.model.data.AiutaImagePickerPredefinedModelFeatureData
import com.aiuta.fashionsdk.configuration.features.picker.model.icons.AiutaImagePickerPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.model.strings.AiutaImagePickerPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImagePickerPredefinedModelFeature(
    public val data: AiutaImagePickerPredefinedModelFeatureData?,
    public val icons: AiutaImagePickerPredefinedModelFeatureIcons,
    public val strings: AiutaImagePickerPredefinedModelFeatureStrings,
) : AiutaFeature {
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

public inline fun AiutaImagePickerFeature.Builder.predefinedModels(
    block: AiutaImagePickerPredefinedModelFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    predefinedModels = AiutaImagePickerPredefinedModelFeature.Builder().apply(block).build()
}
