package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

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

internal val AiutaOnboardingBestResultsPageFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "onboardingBestResultsPageTitle",
            string = onboardingBestResultsPageTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "onboardingBestResultsTitle",
            string = onboardingBestResultsTitle,
        ),
        AiutaStringValidationContainer(
            propertyName = "onboardingBestResultsDescription",
            string = onboardingBestResultsDescription,
        ),
    )
