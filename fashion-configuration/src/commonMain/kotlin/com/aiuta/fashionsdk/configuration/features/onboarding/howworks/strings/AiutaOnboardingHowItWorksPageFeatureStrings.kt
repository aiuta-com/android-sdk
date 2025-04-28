package com.aiuta.fashionsdk.configuration.features.onboarding.howworks.strings

public interface AiutaOnboardingHowItWorksPageFeatureStrings {
    public val onboardingHowItWorksPageTitle: String?
    public val onboardingHowItWorksTitle: String
    public val onboardingHowItWorksDescription: String

    public class Default : AiutaOnboardingHowItWorksPageFeatureStrings {
        override val onboardingHowItWorksPageTitle: String = "<b>Step 1/3</b> - How it works"
        override val onboardingHowItWorksTitle: String = "Try on before buying"
        override val onboardingHowItWorksDescription: String = "Upload a photo and see how items look on you"
    }
}
