package com.aiuta.fashionsdk.tryon.compose.defaults.features.welcome

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.welcomeScreen
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.welcome.DefaultAiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.welcome.DefaultAiutaWelcomeScreenFeatureImages

public fun AiutaTryOnFeatures.Builder.defaultWelcomeScreen() {
    welcomeScreen {
        images = DefaultAiutaWelcomeScreenFeatureImages()
        icons = DefaultAiutaWelcomeScreenFeatureIcons()
        strings = AiutaWelcomeScreenFeatureStrings.Default()
        typography = AiutaWelcomeScreenFeatureTypography.Default()
    }
}
