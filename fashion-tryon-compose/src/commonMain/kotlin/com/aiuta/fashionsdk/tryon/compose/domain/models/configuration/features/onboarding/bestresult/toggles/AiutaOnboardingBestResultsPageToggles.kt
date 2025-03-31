package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.bestresult.toggles

public interface AiutaOnboardingBestResultsPageToggles {
    public val reduceOnboardingBestResultsShadows: Boolean

    public class Default : AiutaOnboardingBestResultsPageToggles {
        override val reduceOnboardingBestResultsShadows: Boolean = false
    }
}
