package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.icons.AiutaImageSelectorPredefinedModelIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.strings.AiutaImageSelectorPredefinedModelStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorPredefinedModel private constructor(
    public val icons: AiutaImageSelectorPredefinedModelIcons,
    public val strings: AiutaImageSelectorPredefinedModelStrings,
) {
    @AiutaDsl
    public class Builder {
        public var icons: AiutaImageSelectorPredefinedModelIcons? = null
        public var strings: AiutaImageSelectorPredefinedModelStrings? = null

        public fun build(): AiutaImageSelectorPredefinedModel {
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
) {
    predefinedModels = AiutaImageSelectorPredefinedModel.Builder().apply(block).build()
}
