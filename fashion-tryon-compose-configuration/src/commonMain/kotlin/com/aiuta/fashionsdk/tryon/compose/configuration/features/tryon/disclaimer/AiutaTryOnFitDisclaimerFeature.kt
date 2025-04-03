package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.icons.AiutaTryOnFitDisclaimerFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.strings.AiutaTryOnFitDisclaimerFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaTryOnFitDisclaimerFeature private constructor(
    public val icons: AiutaTryOnFitDisclaimerFeatureIcons,
    public val strings: AiutaTryOnFitDisclaimerFeatureStrings,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaTryOnFitDisclaimerFeatureIcons? = null
        public var strings: AiutaTryOnFitDisclaimerFeatureStrings? = null

        public override fun build(): AiutaTryOnFitDisclaimerFeature {
            val parentClass = "AiutaTryOnFitDisclaimerFeature"

            return AiutaTryOnFitDisclaimerFeature(
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

public inline fun AiutaTryOnFeature.Builder.fitDisclaimer(
    block: AiutaTryOnFitDisclaimerFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    fitDisclaimer = AiutaTryOnFitDisclaimerFeature.Builder().apply(block).build()
}
