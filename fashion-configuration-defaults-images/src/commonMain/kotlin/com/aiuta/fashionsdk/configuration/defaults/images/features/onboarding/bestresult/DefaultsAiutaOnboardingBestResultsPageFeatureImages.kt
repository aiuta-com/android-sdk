package com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.bestresult

import com.aiuta.fashion_configuration_defaults_images.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_bad_image_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_bad_image_2
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_good_image_2
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageFeatureImages

/**
 * Default implementation of [AiutaOnboardingBestResultsPageFeatureImages].
 *
 * This class provides the default image resources for the onboarding best results page,
 * including examples of good and bad try-on results to help users understand
 * what makes for a successful try-on experience.
 *
 * @property onboardingBestResultsGood List of images showing good examples of try-on results
 * @property onboardingBestResultsBad List of images showing bad examples of try-on results
 */
public class DefaultsAiutaOnboardingBestResultsPageFeatureImages : AiutaOnboardingBestResultsPageFeatureImages {
    override val onboardingBestResultsGood: List<AiutaDrawableResource> = listOf(
        AiutaComposeDrawableResource(Res.drawable.onboarding_good_image_1),
        AiutaComposeDrawableResource(Res.drawable.onboarding_good_image_2),
    )
    override val onboardingBestResultsBad: List<AiutaDrawableResource> = listOf(
        AiutaComposeDrawableResource(Res.drawable.onboarding_bad_image_1),
        AiutaComposeDrawableResource(Res.drawable.onboarding_bad_image_2),
    )
}
