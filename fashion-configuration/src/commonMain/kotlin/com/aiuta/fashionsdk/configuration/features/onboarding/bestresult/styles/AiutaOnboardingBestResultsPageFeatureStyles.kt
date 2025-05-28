package com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.styles

/**
 * Interface defining style configurations for the best results page of the onboarding flow.
 * 
 * This interface provides style options for customizing the visual appearance
 * of the best results showcase.
 */
public interface AiutaOnboardingBestResultsPageFeatureStyles {
    /**
     * Flag indicating whether to reduce shadow effects.
     * When true, shadows are minimized for a flatter appearance.
     */
    public val reduceOnboardingBestResultsShadows: Boolean

    /**
     * Default implementation of [AiutaOnboardingBestResultsPageFeatureStyles].
     * 
     * Provides standard style settings with full shadow effects enabled.
     */
    public class Default : AiutaOnboardingBestResultsPageFeatureStyles {
        override val reduceOnboardingBestResultsShadows: Boolean = false
    }
}
