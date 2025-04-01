package com.aiuta.fashionsdk.tryon.compose.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature

@Immutable
public class AiutaTryOnFeatures internal constructor(
    public val consent: AiutaConsentFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
) {
    @AiutaDsl
    public class Builder {
        public var consent: AiutaConsentFeature? = null
        public var onboarding: AiutaOnboardingFeature? = null
        public var welcomeScreen: AiutaWelcomeScreenFeature? = null

        public fun build(): AiutaTryOnFeatures {
            // Init default
            val internalConsent = consent
            val internalOnboarding = onboarding
            val internalWelcomeScreen = welcomeScreen

            return AiutaTryOnFeatures(
                consent = internalConsent,
                onboarding = internalOnboarding,
                welcomeScreen = internalWelcomeScreen,
            )
        }
    }
}

public inline fun aiutaTryOnFeatures(
    block: AiutaTryOnFeatures.Builder.() -> Unit,
): AiutaTryOnFeatures = AiutaTryOnFeatures.Builder().apply(block).build()
