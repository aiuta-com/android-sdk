package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings

import com.aiuta.fashionsdk.tryon.compose.configuration.internal.validation.models.AiutaStringValidationContainer

public interface AiutaOnboardingFeatureStrings {
    public val onboardingButtonNext: String
    public val onboardingButtonStart: String

    public class Default : AiutaOnboardingFeatureStrings {
        override val onboardingButtonNext: String = "Next"
        override val onboardingButtonStart: String = "Start"
    }
}

internal val AiutaOnboardingFeatureStrings.validationList
    get() = listOf(
        AiutaStringValidationContainer(
            propertyName = "onboardingButtonNext",
            string = onboardingButtonNext,
        ),
        AiutaStringValidationContainer(
            propertyName = "onboardingButtonStart",
            string = onboardingButtonStart,
        ),
    )
