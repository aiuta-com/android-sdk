package com.aiuta.fashionsdk.configuration.features.share.watermark

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.configuration.features.share.watermark.images.AiutaShareWatermarkFeatureImages
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for watermarking shared images in the SDK.
 *
 * This feature enables customization of watermarks that are applied to images
 * when they are shared, allowing for branding and attribution.
 *
 * @property images Configuration for watermark images
 */
public class AiutaShareWatermarkFeature(
    public val images: AiutaShareWatermarkFeatureImages,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaShareWatermarkFeature] instances.
     *
     * @property images Configuration for watermark images
     */
    public class Builder : AiutaFeature.Builder {
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

/**
 * DSL function for configuring the watermark feature.
 *
 * This extension provides a convenient DSL for configuring the watermark
 * feature as part of the share feature setup.
 *
 * ```kotlin
 * watermark {
 *     images = ...
 * }
 * ```
 *
 * @param block Configuration block for the watermark feature
 * @return The updated [AiutaShareFeature.Builder]
 */
public inline fun AiutaShareFeature.Builder.watermark(
    block: AiutaShareWatermarkFeature.Builder.() -> Unit,
): AiutaShareFeature.Builder = apply {
    watermark = AiutaShareWatermarkFeature.Builder().apply(block).build()
}
