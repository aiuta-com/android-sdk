package com.aiuta.fashionsdk.configuration.features.powerby

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.powerby.colors.AiutaPoweredByFeatureColors
import com.aiuta.fashionsdk.configuration.features.powerby.strings.AiutaPoweredByFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaPoweredByFeature private constructor(
    public val strings: AiutaPoweredByFeatureStrings,
    public val colors: AiutaPoweredByFeatureColors,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaPoweredByFeatureStrings? = null
        public var colors: AiutaPoweredByFeatureColors? = null

        public override fun build(): AiutaPoweredByFeature {
            val parentClass = "AiutaPoweredByFeature"

            return AiutaPoweredByFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                colors = colors.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "colors",
                ),
            )
        }
    }
}

public inline fun AiutaFeatures.Builder.poweredBy(
    block: AiutaPoweredByFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    poweredBy = AiutaPoweredByFeature.Builder().apply(block).build()
}
