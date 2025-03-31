package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

@Composable
@ReadOnlyComposable
internal fun strictOnboardingFeature(): AiutaOnboardingFeature {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return checkFeatureAvailability(
        name = "AiutaOnboardingFeature",
        feature = aiutaConfiguration.features.onboarding,
    )
}

@Composable
@ReadOnlyComposable
internal fun strictBestResultFeature(): AiutaOnboardingBestResultsPage {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return checkFeatureAvailability(
        name = "AiutaOnboardingBestResultsPage",
        feature = aiutaConfiguration.features.onboarding?.bestResultsPage,
    )
}

internal fun AiutaTryOnConfiguration.isOnboardingFeatureAvailable(): Boolean {
    return features.onboarding != null
}
