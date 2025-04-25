package com.aiuta.fashionsdk.configuration.features.features.selector.model

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.configuration.features.features.selector.model.icons.AiutaImageSelectorPredefinedModelFeatureIcons
import com.aiuta.fashionsdk.configuration.features.features.selector.model.strings.AiutaImageSelectorPredefinedModelFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImageSelectorPredefinedModelFeature private constructor(
    public val icons: AiutaImageSelectorPredefinedModelFeatureIcons,
    public val strings: AiutaImageSelectorPredefinedModelFeatureStrings,
) : AiutaTryOnConfigurationFeature {
    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaImageSelectorPredefinedModelFeatureIcons? = null
        public var strings: AiutaImageSelectorPredefinedModelFeatureStrings? = null

        public override fun build(): AiutaImageSelectorPredefinedModelFeature {
            val parentClass = "AiutaImageSelectorPredefinedModelFeature"

            return AiutaImageSelectorPredefinedModelFeature(
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

public inline fun AiutaImageSelectorFeature.Builder.predefinedModels(
    block: AiutaImageSelectorPredefinedModelFeature.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    predefinedModels = AiutaImageSelectorPredefinedModelFeature.Builder().apply(block).build()
}
