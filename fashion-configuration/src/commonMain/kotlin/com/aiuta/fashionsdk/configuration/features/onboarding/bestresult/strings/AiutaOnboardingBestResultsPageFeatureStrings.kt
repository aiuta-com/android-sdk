package com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.strings

public interface AiutaOnboardingBestResultsPageFeatureStrings {
    public val onboardingBestResultsPageTitle: String?
    public val onboardingBestResultsTitle: String
    public val onboardingBestResultsDescription: String

    public class Default : AiutaOnboardingBestResultsPageFeatureStrings {
        override val onboardingBestResultsPageTitle: String = "<b>Step 2/3</b> - For best result"
        override val onboardingBestResultsTitle: String = "For best results"
        override val onboardingBestResultsDescription: String = "Use a photo with good lighting, stand straight a plain background"
    }
}
