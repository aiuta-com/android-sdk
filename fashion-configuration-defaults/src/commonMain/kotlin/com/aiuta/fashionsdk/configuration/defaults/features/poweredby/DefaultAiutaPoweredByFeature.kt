package com.aiuta.fashionsdk.configuration.defaults.features.poweredby

import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.powerby.colors.AiutaPoweredByFeatureColors
import com.aiuta.fashionsdk.configuration.features.powerby.poweredBy
import com.aiuta.fashionsdk.configuration.features.powerby.strings.AiutaPoweredByFeatureStrings

public fun AiutaFeatures.Builder.defaultPoweredBy(): AiutaFeatures.Builder = poweredBy {
    strings = AiutaPoweredByFeatureStrings.Default()
    colors = AiutaPoweredByFeatureColors.Default()
}
