package com.aiuta.fashionsdk.tryon.compose.defaults.features.share

import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.share.share
import com.aiuta.fashionsdk.configuration.features.share.strings.AiutaShareFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.share.DefaultAiutaShareFeatureIcons

public fun AiutaFeatures.Builder.defaultShare() {
    share {
        icons = DefaultAiutaShareFeatureIcons()
        strings = AiutaShareFeatureStrings.Default()
    }
}
