package com.aiuta.fashionsdk.tryon.compose.defaults

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.aiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.language.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.defaults.features.consent.defaultConsent
import com.aiuta.fashionsdk.tryon.compose.defaults.features.onboarding.defaultOnboarding
import com.aiuta.fashionsdk.tryon.compose.defaults.features.welcome.defaultWelcomeScreen

public class DefaultAiutaTryOnConfiguration private constructor() {
    @AiutaDsl
    public class Builder {
        private var aiutaTryOnConfigurationBuilder: AiutaTryOnConfiguration.Builder =
            AiutaTryOnConfiguration.Builder()

        public var aiuta: Aiuta? = null
            set(newAiuta) {
                newAiuta?.let { aiutaTryOnConfigurationBuilder.aiuta = newAiuta }
                field = newAiuta
            }

        public fun build(): AiutaTryOnConfiguration = aiutaTryOnConfigurationBuilder.apply {
            aiutaTryOnFeatures {
                defaultWelcomeScreen()
                defaultOnboarding()
                defaultConsent()
            }
            language = EnglishLanguage(
                brand = "YOUR brand",
                termsOfServiceUrl = "https://brand.com/tos",
                privacyPolicyUrl = "https://brand.com/pp",
                onboardingPageConsentSupplementaryPoints = emptyList(),
            )
        }.build()
    }
}

public inline fun defaultAiutaTryOnConfiguration(
    block: DefaultAiutaTryOnConfiguration.Builder.() -> Unit,
): AiutaTryOnConfiguration = DefaultAiutaTryOnConfiguration.Builder().apply(block).build()
