package com.aiuta.fashionsdk.configuration.defaults.features.share

import com.aiuta.fashionsdk.configuration.defaults.icons.features.share.DefaultAiutaShareFeatureIcons
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.share.share
import com.aiuta.fashionsdk.configuration.features.share.strings.AiutaShareFeatureStrings

/**
 * Configures the default share feature for the Aiuta SDK.
 *
 * This function sets up the share feature with default icons and strings.
 *
 * @return The updated [AiutaFeatures.Builder] instance.
 */
public fun AiutaFeatures.Builder.defaultShare(): AiutaFeatures.Builder = share {
    icons = DefaultAiutaShareFeatureIcons()
    strings = AiutaShareFeatureStrings.Default()
}
