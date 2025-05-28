package com.aiuta.fashionsdk.configuration.features.onboarding.strings

/**
 * Interface defining text strings used in the onboarding feature.
 *
 * This interface provides strings for navigation buttons in the onboarding flow.
 */
public interface AiutaOnboardingFeatureStrings {
    /**
     * Text for the "Next" button used to navigate through onboarding pages.
     */
    public val onboardingButtonNext: String

    /**
     * Text for the "Start" button used to begin the onboarding process.
     */
    public val onboardingButtonStart: String

    /**
     * Default implementation of [AiutaOnboardingFeatureStrings].
     *
     * Provides standard English text for the onboarding buttons.
     */
    public class Default : AiutaOnboardingFeatureStrings {
        override val onboardingButtonNext: String = "Next"
        override val onboardingButtonStart: String = "Start"
    }
}
