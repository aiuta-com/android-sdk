package com.aiuta.fashionsdk.configuration.features.onboarding.tryon.images

import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

public interface AiutaOnboardingTryOnPageFeatureImages {
    public class OnboardingTryOnItem(
        public val itemPhoto: AiutaDrawableResource,
        public val itemPreview: AiutaDrawableResource,
    )

    public val onboardingTryOnItems: List<OnboardingTryOnItem>
}
