package com.aiuta.fashionsdk.configuration.features.tryon.cart

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.cart.handler.AiutaTryOnCartFeatureHandler
import com.aiuta.fashionsdk.configuration.features.tryon.cart.strings.AiutaTryOnCartFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnCartFeature(
    public val strings: AiutaTryOnCartFeatureStrings,
    public val handler: AiutaTryOnCartFeatureHandler,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaTryOnCartFeatureStrings? = null
        public var handler: AiutaTryOnCartFeatureHandler? = null

        public override fun build(): AiutaTryOnCartFeature {
            val parentClass = "AiutaTryOnCartFeature"

            return AiutaTryOnCartFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                handler = handler.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "handler",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.cart(
    block: AiutaTryOnCartFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    cart = AiutaTryOnCartFeature.Builder().apply(block).build()
}
