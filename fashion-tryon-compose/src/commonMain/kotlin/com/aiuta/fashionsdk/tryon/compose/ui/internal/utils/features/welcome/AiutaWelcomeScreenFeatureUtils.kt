package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

@Composable
@ReadOnlyComposable
internal fun strictWelcomeScreenFeature(): AiutaWelcomeScreenFeature {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return checkFeatureAvailability(
        name = "AiutaWelcomeScreenFeature",
        feature = aiutaConfiguration.features.welcomeScreen,
    )
}

internal fun AiutaTryOnConfiguration.isWelcomeScreenFeatureAvailable(): Boolean = features.welcomeScreen != null
