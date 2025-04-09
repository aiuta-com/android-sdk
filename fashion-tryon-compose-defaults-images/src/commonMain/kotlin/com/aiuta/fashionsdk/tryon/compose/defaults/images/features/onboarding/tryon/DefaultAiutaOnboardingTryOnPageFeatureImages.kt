package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.tryon

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_2
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_3
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_2
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_3
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageFeatureImages
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource

public class DefaultAiutaOnboardingTryOnPageFeatureImages : AiutaOnboardingTryOnPageFeatureImages {
    override val onboardingTryOnItems: List<AiutaOnboardingTryOnPageFeatureImages.OnboardingTryOnItem> =
        listOf(
            AiutaOnboardingTryOnPageFeatureImages.OnboardingTryOnItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_1),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_1),
            ),
            AiutaOnboardingTryOnPageFeatureImages.OnboardingTryOnItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_2),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_2),
            ),
            AiutaOnboardingTryOnPageFeatureImages.OnboardingTryOnItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_3),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_3),
            ),
        )
}
