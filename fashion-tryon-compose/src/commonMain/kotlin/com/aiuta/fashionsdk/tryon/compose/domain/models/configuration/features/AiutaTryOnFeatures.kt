package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.AiutaWelcomeScreenFeature

@Immutable
public class AiutaTryOnFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val consent: AiutaConsentFeature?,
) {
    public class Builder {
        private var welcomeScreen: AiutaWelcomeScreenFeature? = null
        private var onboarding: AiutaOnboardingFeature? = null
        private var consent: AiutaConsentFeature? = null

        public fun setWelcomeScreen(welcomeScreen: AiutaWelcomeScreenFeature): Builder {
            return apply { this.welcomeScreen = welcomeScreen }
        }

        public fun setOnboarding(onboarding: AiutaOnboardingFeature): Builder {
            return apply { this.onboarding = onboarding }
        }

        public fun setOnboarding(consent: AiutaConsentFeature): Builder {
            return apply { this.consent = consent }
        }

        public fun build(): AiutaTryOnFeatures {
            // Init default
            val internalWelcomeScreen = welcomeScreen
            val internalOnboarding = onboarding
            val internalConsent = consent

            return AiutaTryOnFeatures(
                welcomeScreen = internalWelcomeScreen,
                onboarding = internalOnboarding,
                consent = internalConsent,
            )
        }
    }
}
