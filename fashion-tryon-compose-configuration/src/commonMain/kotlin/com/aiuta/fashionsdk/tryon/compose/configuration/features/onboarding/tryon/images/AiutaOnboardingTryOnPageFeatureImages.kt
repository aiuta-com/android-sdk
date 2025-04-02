package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images

import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage

public interface AiutaOnboardingTryOnPageFeatureImages {
    public class OnboardingTryOnItem(
        public val itemPhoto: AiutaImage,
        public val itemPreview: AiutaImage,
    )

    public val onboardingTryOnItems: List<OnboardingTryOnItem>
}
