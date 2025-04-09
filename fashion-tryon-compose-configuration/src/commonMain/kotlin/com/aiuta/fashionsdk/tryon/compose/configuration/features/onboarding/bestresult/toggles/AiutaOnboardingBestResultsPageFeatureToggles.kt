package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.toggles

public interface AiutaOnboardingBestResultsPageFeatureToggles {
    public val reduceOnboardingBestResultsShadows: Boolean

    public class Default : AiutaOnboardingBestResultsPageFeatureToggles {
        override val reduceOnboardingBestResultsShadows: Boolean = false
    }
}
