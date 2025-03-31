package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.welcome.AiutaWelcomeScreenFeature

@Immutable
public class AiutaTryOnFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
) {
    public class Builder {
        private var welcomeScreen: AiutaWelcomeScreenFeature? = null
        private var onboarding: AiutaOnboardingFeature? = null

        public fun setWelcomeScreen(welcomeScreen: AiutaWelcomeScreenFeature): Builder {
            return apply { this.welcomeScreen = welcomeScreen }
        }

        public fun setOnboarding(onboarding: AiutaOnboardingFeature): Builder {
            return apply { this.onboarding = onboarding }
        }

        public fun build(): AiutaTryOnFeatures {
            // Init default
            val internalWelcomeScreen = welcomeScreen
            val internalOnboarding = onboarding

            return AiutaTryOnFeatures(
                welcomeScreen = internalWelcomeScreen,
                onboarding = internalOnboarding,
            )
        }
    }
}
