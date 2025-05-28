package com.aiuta.fashionsdk.configuration.features.tryon.validation

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.validation.strings.AiutaTryOnInputImageValidationFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the try-on input image validation feature.
 *
 * This feature manages the validation of user-provided images for try-on generation,
 * ensuring that images meet the required criteria for successful try-on results.
 *
 * Required components:
 * - [strings]: Text content for validation messages and instructions
 *
 * @property strings Text content for validation messages and instructions
 */
public class AiutaTryOnInputImageValidationFeature(
    public val strings: AiutaTryOnInputImageValidationFeatureStrings,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaTryOnInputImageValidationFeature] instances.
     *
     * This builder ensures all required properties are set before creating the feature instance.
     */
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

/**
 * DSL function for configuring the input image validation feature.
 *
 * This function allows for DSL-style configuration of the input image validation feature
 * within the try-on feature configuration.
 *
 * ```kotlin
 * tryOn {
 *     inputImageValidation {
 *         strings = ...
 *     }
 * }
 * ```
 *
 * @param block Configuration block for the input image validation feature
 * @return The updated try-on feature builder
 */
public inline fun AiutaTryOnFeature.Builder.inputImageValidation(
    block: AiutaTryOnInputImageValidationFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    inputImageValidation = AiutaTryOnInputImageValidationFeature.Builder().apply(block).build()
}
