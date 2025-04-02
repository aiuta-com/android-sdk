package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.bestresult

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_bad_image_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_bad_image_2
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_good_image_2
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageFeatureImages

public class DefaultsAiutaOnboardingBestResultsPageFeatureImages : AiutaOnboardingBestResultsPageFeatureImages {
    override val onboardingBestResultsGood: List<AiutaImage> = listOf(
        AiutaResourceImage(Res.drawable.onboarding_good_image_1),
        AiutaResourceImage(Res.drawable.onboarding_good_image_2),
    )
    override val onboardingBestResultsBad: List<AiutaImage> = listOf(
        AiutaResourceImage(Res.drawable.onboarding_bad_image_1),
        AiutaResourceImage(Res.drawable.onboarding_bad_image_2),
    )
}
