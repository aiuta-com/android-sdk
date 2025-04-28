package com.aiuta.fashionsdk.configuration.features.onboarding.howworks.images

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

public interface AiutaOnboardingHowItWorksPageFeatureImages {
    public class OnboardingHowItWorksItem(
        public val itemPhoto: AiutaDrawableResource,
        public val itemPreview: AiutaDrawableResource,
    )

    public val onboardingHowItWorksItems: List<OnboardingHowItWorksItem>
}
