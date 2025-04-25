package com.aiuta.fashionsdk.configuration.features.features.share.watermark

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.configuration.features.features.share.watermark.images.AiutaShareWatermarkFeatureImages
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaShareWatermarkFeature(
    public val images: AiutaShareWatermarkFeatureImages,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var images: AiutaShareWatermarkFeatureImages? = null

        public override fun build(): AiutaShareWatermarkFeature {
            val parentClass = "AiutaShareWatermarkFeature"

            return AiutaShareWatermarkFeature(
                images = images.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

public inline fun AiutaShareFeature.Builder.watermark(
    block: AiutaShareWatermarkFeature.Builder.() -> Unit,
): AiutaShareFeature.Builder = apply {
    watermark = AiutaShareWatermarkFeature.Builder().apply(block).build()
}
