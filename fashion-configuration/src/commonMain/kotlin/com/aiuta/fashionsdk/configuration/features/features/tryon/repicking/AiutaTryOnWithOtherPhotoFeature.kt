package com.aiuta.fashionsdk.configuration.features.features.tryon.repicking

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.features.tryon.repicking.icons.AiutaTryOnWithOtherPhotoFeatureIcons
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnWithOtherPhotoFeature private constructor(
    public val icons: AiutaTryOnWithOtherPhotoFeatureIcons,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
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

public inline fun AiutaTryOnFeature.Builder.repicking(
    block: AiutaTryOnWithOtherPhotoFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    repicking = AiutaTryOnWithOtherPhotoFeature.Builder().apply(block).build()
}
