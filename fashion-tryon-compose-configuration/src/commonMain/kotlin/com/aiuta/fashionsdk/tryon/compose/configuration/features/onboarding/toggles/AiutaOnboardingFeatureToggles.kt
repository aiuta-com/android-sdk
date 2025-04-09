package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.toggles

public interface AiutaOnboardingFeatureToggles {
    public val isAppBarExtended: Boolean

    public class Default : AiutaOnboardingFeatureToggles {
        override val isAppBarExtended: Boolean = true
    }
}
