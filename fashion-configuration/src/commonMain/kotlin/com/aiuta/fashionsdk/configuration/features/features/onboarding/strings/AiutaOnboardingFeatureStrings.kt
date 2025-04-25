package com.aiuta.fashionsdk.configuration.features.features.onboarding.strings

public interface AiutaOnboardingFeatureStrings {
    public val onboardingButtonNext: String
    public val onboardingButtonStart: String

    public class Default : AiutaOnboardingFeatureStrings {
        override val onboardingButtonNext: String = "Next"
        override val onboardingButtonStart: String = "Start"
    }
}
