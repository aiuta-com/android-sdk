package com.aiuta.fashionsdk.configuration.features.onboarding.howworks.images

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

/**
 * Interface defining images used in the "How It Works" page of the onboarding flow.
 *
 * This interface provides a collection of image pairs that demonstrate the app's
 * functionality through before/after examples.
 */
public interface AiutaOnboardingHowItWorksPageFeatureImages {
    /**
     * Data class representing a single step in the "How It Works" demonstration.
     *
     * @property itemPhoto The original photo used as input
     * @property itemPreview The resulting preview after processing
     */
    public class OnboardingHowItWorksItem(
        public val itemPhoto: AiutaDrawableResource,
        public val itemPreview: AiutaDrawableResource,
    )

    /**
     * List of demonstration items showing the app's functionality.
     * Each item contains a pair of images showing the before and after states.
     */
    public val onboardingHowItWorksItems: List<OnboardingHowItWorksItem>
}
