package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.tryon

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_2
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_item_3
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_2
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_main_3
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.images.AiutaOnboardingTryOnPageImages

public class DefaultAiutaOnboardingTryOnPageImages : AiutaOnboardingTryOnPageImages {
    override val onboardingTryOnItems: List<AiutaOnboardingTryOnPageImages.OnboardingTryOnItem> =
        listOf(
            AiutaOnboardingTryOnPageImages.OnboardingTryOnItem(
                itemPhoto = AiutaResourceImage(Res.drawable.onboarding_main_1),
                itemPreview = AiutaResourceImage(Res.drawable.onboarding_item_1),
            ),
            AiutaOnboardingTryOnPageImages.OnboardingTryOnItem(
                itemPhoto = AiutaResourceImage(Res.drawable.onboarding_main_2),
                itemPreview = AiutaResourceImage(Res.drawable.onboarding_item_2),
            ),
            AiutaOnboardingTryOnPageImages.OnboardingTryOnItem(
                itemPhoto = AiutaResourceImage(Res.drawable.onboarding_main_3),
                itemPreview = AiutaResourceImage(Res.drawable.onboarding_item_3),
            ),
        )
}
