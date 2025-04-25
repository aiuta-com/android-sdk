package com.aiuta.fashionsdk.configuration.features.tryon.other

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.other.icons.AiutaTryOnWithOtherPhotoFeatureIcons
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnWithOtherPhotoFeature private constructor(
    public val icons: AiutaTryOnWithOtherPhotoFeatureIcons,
) : AiutaFeature {

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

public inline fun AiutaTryOnFeature.Builder.otherPhoto(
    block: AiutaTryOnWithOtherPhotoFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    otherPhoto = AiutaTryOnWithOtherPhotoFeature.Builder().apply(block).build()
}
