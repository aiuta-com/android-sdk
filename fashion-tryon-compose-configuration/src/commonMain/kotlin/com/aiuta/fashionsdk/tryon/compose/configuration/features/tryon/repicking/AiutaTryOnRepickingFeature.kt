package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.repicking

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.strings.AiutaTryOnLoadingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.repicking.icons.AiutaTryOnRepickingFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaTryOnRepickingFeature private constructor(
    public val icons: AiutaTryOnRepickingFeatureIcons,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaTryOnRepickingFeatureIcons? = null
        public var strings: AiutaTryOnLoadingPageFeatureStrings? = null

        public override fun build(): AiutaTryOnRepickingFeature {
            val parentClass = "AiutaTryOnRepickingFeature"

            return AiutaTryOnRepickingFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.repicking(
    block: AiutaTryOnRepickingFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    repicking = AiutaTryOnRepickingFeature.Builder().apply(block).build()
}
