package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding

import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.AiutaOnboardingTryOnPage

public class AiutaOnboardingFeature(
    public val tryOnPage: AiutaOnboardingTryOnPage,
    public val bestResultsPage: AiutaOnboardingBestResultsPage? = null,
    public val strings: AiutaOnboardingFeatureStrings,
    public val shapes: AiutaOnboardingFeatureShapes,
)
