package com.aiuta.fashionsdk.configuration.features.picker.model

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.model.icons.AiutaImageSelectorPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.model.strings.AiutaImageSelectorPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImagePickerPredefinedModelFeature(
    public val icons: AiutaImageSelectorPredefinedModelFeatureIcons,
    public val strings: AiutaImageSelectorPredefinedModelFeatureStrings,
) : AiutaFeature {
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImageSelectorPredefinedModelFeatureIcons? = null
        public var strings: AiutaImageSelectorPredefinedModelFeatureStrings? = null

        public override fun build(): AiutaImagePickerPredefinedModelFeature {
            val parentClass = "AiutaImagePickerPredefinedModelFeature"

            return AiutaImagePickerPredefinedModelFeature(
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
