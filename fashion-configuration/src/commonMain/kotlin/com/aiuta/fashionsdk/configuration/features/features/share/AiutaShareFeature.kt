package com.aiuta.fashionsdk.configuration.features.features.share

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.share.dataprovider.AiutaShareFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.features.share.icons.AiutaShareFeatureIcons
import com.aiuta.fashionsdk.configuration.features.features.share.strings.AiutaShareFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.share.watermark.AiutaShareWatermarkFeature
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaShareFeature private constructor(
    // Features
    public val watermark: AiutaShareWatermarkFeature?,
    // General
    public val icons: AiutaShareFeatureIcons,
    public val strings: AiutaShareFeatureStrings,
    public val dataProvider: AiutaShareFeatureDataProvider?,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var watermark: AiutaShareWatermarkFeature? = null
        public var icons: AiutaShareFeatureIcons? = null
        public var strings: AiutaShareFeatureStrings? = null
        public var dataProvider: AiutaShareFeatureDataProvider? = null

        public override fun build(): AiutaShareFeature {
            val parentClass = "AiutaShareFeature"

            return AiutaShareFeature(
                watermark = watermark,
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
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
