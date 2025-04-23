package com.aiuta.fashionsdk.tryon.compose.defaults.features.share

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.share
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.strings.AiutaShareFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.watermark.images.AiutaShareWatermarkFeatureImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.watermark.watermark
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.share.DefaultAiutaShareFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.welcome.DefaultAiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

public fun AiutaTryOnConfigurationFeatures.Builder.defaultShare() {
    share {
        // TODO
        watermark {
            images = object : AiutaShareWatermarkFeatureImages {
                override val logo: AiutaDrawableResource = DefaultAiutaWelcomeScreenFeatureIcons().welcome82.iconResource
            }
        }
        icons = DefaultAiutaShareFeatureIcons()
        strings = AiutaShareFeatureStrings.Default()
    }
}
