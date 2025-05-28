package com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.images

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource

/**
 * Interface defining images used in the best results page of the onboarding flow.
 * 
 * This interface provides collections of images for showcasing good and bad
 * examples of app usage in the best results showcase.
 */
public interface AiutaOnboardingBestResultsPageFeatureImages {
    /**
     * List of images showing good examples of app usage.
     * These images demonstrate optimal results and best practices.
     */
    public val onboardingBestResultsGood: List<AiutaDrawableResource>

    /**
     * List of images showing bad examples of app usage.
     * These images demonstrate common mistakes and what to avoid.
     */
    public val onboardingBestResultsBad: List<AiutaDrawableResource>
}
