package com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.bestresult

import com.aiuta.fashion_configuration_defaults_images.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_bad_image_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_bad_image_2
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_good_image_2
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

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
