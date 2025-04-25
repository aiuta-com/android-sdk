package com.aiuta.fashionsdk.configuration.defaults.features.share

import com.aiuta.fashionsdk.configuration.defaults.icons.features.share.DefaultAiutaShareFeatureIcons
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.share.share
import com.aiuta.fashionsdk.configuration.features.share.strings.AiutaShareFeatureStrings

public fun AiutaFeatures.Builder.defaultShare() {
    share {
        icons = DefaultAiutaShareFeatureIcons()
        strings = AiutaShareFeatureStrings.Default()
    }
}
