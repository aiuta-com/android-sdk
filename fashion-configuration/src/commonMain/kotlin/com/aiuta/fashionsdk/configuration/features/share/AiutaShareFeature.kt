package com.aiuta.fashionsdk.configuration.features.share

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.share.dataprovider.AiutaShareFeatureDataProviderCustom
import com.aiuta.fashionsdk.configuration.features.share.icons.AiutaShareFeatureIcons
import com.aiuta.fashionsdk.configuration.features.share.strings.AiutaShareFeatureStrings
import com.aiuta.fashionsdk.configuration.features.share.watermark.AiutaShareWatermarkFeature
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the share feature in the SDK.
 *
 * This feature enables sharing functionality for generated images and try-on results,
 * allowing customization of watermarks, icons, strings, and data providers.
 *
 * @property watermark Optional configuration for image watermarks
 * @property icons Icons used in the share interface
 * @property strings Text strings used in the share interface
 * @property dataProvider Optional custom data provider for share functionality
 */
public class AiutaShareFeature(
    // Features
    public val watermark: AiutaShareWatermarkFeature?,
    // General
    public val icons: AiutaShareFeatureIcons,
    public val strings: AiutaShareFeatureStrings,
    public val dataProvider: AiutaShareFeatureDataProviderCustom?,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaShareFeature] instances.
     *
     * @property watermark Optional configuration for image watermarks
     * @property icons Icons used in the share interface
     * @property strings Text strings used in the share interface
     * @property dataProvider Optional custom data provider for share functionality
     */
    public class Builder : AiutaFeature.Builder {
        public var watermark: AiutaShareWatermarkFeature? = null
        public var icons: AiutaShareFeatureIcons? = null
        public var strings: AiutaShareFeatureStrings? = null
        public var dataProvider: AiutaShareFeatureDataProviderCustom? = null

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

/**
 * DSL function for configuring the share feature.
 *
 * This extension provides a convenient DSL for configuring the share
 * feature as part of the main features setup.
 *
 * ```kotlin
 * share {
 *     watermark {
 *         images = ...
 *     }
 *     icons = ...
 *     strings = ...
 *     dataProvider = ...
 * }
 * ```
 *
 * @param block Configuration block for the share feature
 * @return The updated [AiutaFeatures.Builder]
 */
public inline fun AiutaFeatures.Builder.share(
    block: AiutaShareFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    share = AiutaShareFeature.Builder().apply(block).build()
}
