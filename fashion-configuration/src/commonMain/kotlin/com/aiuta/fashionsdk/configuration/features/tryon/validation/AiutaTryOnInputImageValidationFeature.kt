package com.aiuta.fashionsdk.configuration.features.tryon.validation

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.validation.strings.AiutaTryOnInputImageValidationFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnInputImageValidationFeature private constructor(
    public val strings: AiutaTryOnInputImageValidationFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaTryOnInputImageValidationFeatureStrings? = null

        public override fun build(): AiutaTryOnInputImageValidationFeature {
            val parentClass = "AiutaTryOnInputImageValidationFeature"

            return AiutaTryOnInputImageValidationFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.inputImageValidation(
    block: AiutaTryOnInputImageValidationFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    inputImageValidation = AiutaTryOnInputImageValidationFeature.Builder().apply(block).build()
}
