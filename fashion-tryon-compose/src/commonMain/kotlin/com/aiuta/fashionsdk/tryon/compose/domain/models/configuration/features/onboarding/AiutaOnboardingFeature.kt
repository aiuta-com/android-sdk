package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

public class AiutaOnboardingFeature(
    public val tryOnPage: AiutaOnboardingTryOnPage,
    public val bestResultsPage: AiutaOnboardingBestResultsPage? = null,
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
)

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
