package com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.styles

public interface AiutaOnboardingBestResultsPageFeatureStyles {
    public val reduceOnboardingBestResultsShadows: Boolean

    public class Default : AiutaOnboardingBestResultsPageFeatureStyles {
        override val reduceOnboardingBestResultsShadows: Boolean = false
    }
}
