package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.tryon.strings

public interface AiutaOnboardingTryOnPageStrings {
    public val onboardingTryOnPageTitle: String?
    public val onboardingTryOnTitle: String
    public val onboardingTryOnDescription: String

    public class Default : AiutaOnboardingTryOnPageStrings {
        override val onboardingTryOnPageTitle: String = "<b>Step 1/3</b> - How it works"
        override val onboardingTryOnTitle: String = "Try on before buying"
        override val onboardingTryOnDescription: String = "Upload a photo and see how items look on you"
    }
}
