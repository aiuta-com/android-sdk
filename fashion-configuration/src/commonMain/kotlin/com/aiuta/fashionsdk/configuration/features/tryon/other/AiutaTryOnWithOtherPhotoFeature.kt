package com.aiuta.fashionsdk.configuration.features.tryon.other

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.other.icons.AiutaTryOnWithOtherPhotoFeatureIcons
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the try-on with other photo feature.
 * 
 * This feature manages the ability to use a different photo for try-on generation,
 * allowing users to try on clothes using photos other than their current one.
 * 
 * Required components:
 * - [icons]: Icon resources for the other photo interface
 * 
 * @property icons Icon resources for the other photo interface
 */
public class AiutaTryOnWithOtherPhotoFeature(
    public val icons: AiutaTryOnWithOtherPhotoFeatureIcons,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaTryOnWithOtherPhotoFeature] instances.
     * 
     * This builder ensures all required properties are set before creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaTryOnWithOtherPhotoFeatureIcons? = null

        public override fun build(): AiutaTryOnWithOtherPhotoFeature {
            val parentClass = "AiutaTryOnWithOtherPhotoFeature"

            return AiutaTryOnWithOtherPhotoFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

/**
 * DSL function for configuring the other photo feature.
 * 
 * This function allows for DSL-style configuration of the other photo feature
 * within the try-on feature configuration.
 * 
 * ```kotlin
 * tryOn {
 *     otherPhoto {
 *         icons = ...
 *     }
 * }
 * ```
 * 
 * @param block Configuration block for the other photo feature
 * @return The updated try-on feature builder
 */
public inline fun AiutaTryOnFeature.Builder.otherPhoto(
    block: AiutaTryOnWithOtherPhotoFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    otherPhoto = AiutaTryOnWithOtherPhotoFeature.Builder().apply(block).build()
}
