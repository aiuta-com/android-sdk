package com.aiuta.fashionsdk.tryon.compose.defaults.features.share

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.configuration.features.features.share.share
import com.aiuta.fashionsdk.configuration.features.features.share.strings.AiutaShareFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.share.DefaultAiutaShareFeatureIcons

public fun AiutaTryOnConfigurationFeatures.Builder.defaultShare() {
    share {
        icons = DefaultAiutaShareFeatureIcons()
        strings = AiutaShareFeatureStrings.Default()
    }
}
