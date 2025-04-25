package com.aiuta.fashionsdk.configuration.defaults.features.welcome

import com.aiuta.fashionsdk.configuration.defaults.icons.features.welcome.DefaultAiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.images.features.welcome.DefaultAiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.configuration.features.welcome.welcomeScreen

public fun AiutaFeatures.Builder.defaultWelcomeScreen(): AiutaFeatures.Builder = welcomeScreen {
    images = DefaultAiutaWelcomeScreenFeatureImages()
    icons = DefaultAiutaWelcomeScreenFeatureIcons()
    strings = AiutaWelcomeScreenFeatureStrings.Default()
    typography = AiutaWelcomeScreenFeatureTypography.Default()
}
