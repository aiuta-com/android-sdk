package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.icons.AiutaImageSelectorPredefinedModelIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.strings.AiutaImageSelectorPredefinedModelStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorPredefinedModel private constructor(
    public val icons: AiutaImageSelectorPredefinedModelIcons,
    public val strings: AiutaImageSelectorPredefinedModelStrings,
) : AiutaFeature {
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImageSelectorPredefinedModelIcons? = null
        public var strings: AiutaImageSelectorPredefinedModelStrings? = null

        public override fun build(): AiutaImageSelectorPredefinedModel {
            val parentClass = "AiutaImageSelectorPredefinedModel"

            return AiutaImageSelectorPredefinedModel(
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
    block: AiutaImageSelectorPredefinedModel.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    predefinedModels = AiutaImageSelectorPredefinedModel.Builder().apply(block).build()
}
