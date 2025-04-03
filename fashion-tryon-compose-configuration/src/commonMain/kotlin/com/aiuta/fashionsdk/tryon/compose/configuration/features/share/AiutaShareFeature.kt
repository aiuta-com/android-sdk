package com.aiuta.fashionsdk.tryon.compose.configuration.features.share

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.dataprovider.AiutaShareFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.icons.AiutaShareFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.watermark.AiutaShareWatermarkFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaShareFeature private constructor(
    // Features
    public val watermark: AiutaShareWatermarkFeature?,
    // General
    public val icons: AiutaShareFeatureIcons,
    public val dataProvider: AiutaShareFeatureDataProvider?,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var watermark: AiutaShareWatermarkFeature? = null
        public var icons: AiutaShareFeatureIcons? = null
        public var dataProvider: AiutaShareFeatureDataProvider? = null

        public override fun build(): AiutaShareFeature {
            val parentClass = "AiutaShareFeature"

            return AiutaShareFeature(
                watermark = watermark,
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                dataProvider = dataProvider,
            )
        }
    }
}

public inline fun AiutaTryOnConfigurationFeatures.Builder.share(
    block: AiutaShareFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    share = AiutaShareFeature.Builder().apply(block).build()
}
