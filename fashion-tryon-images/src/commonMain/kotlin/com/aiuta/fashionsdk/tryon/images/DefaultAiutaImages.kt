package com.aiuta.fashionsdk.tryon.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashion_tryon_images.generated.resources.Res
import com.aiuta.fashion_tryon_images.generated.resources.ic_heart
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_bad_image_1
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_bad_image_2
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_good_image_2
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_item_1
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_item_2
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_item_3
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_main_1
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_main_2
import com.aiuta.fashion_tryon_images.generated.resources.onboarding_main_3
import com.aiuta.fashion_tryon_images.generated.resources.selector_empty_small_image_1
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImages
import com.aiuta.fashionsdk.compose.tokens.images.AiutaResourceImage
import com.aiuta.fashionsdk.compose.tokens.images.onboarding.AiutaOnboardingImages

/**
 * Default implementation of [AiutaImages].
 */
public fun defaultAiutaImages(): AiutaImages {
    return AiutaImages(
        preonboardingImage = null,
        onboardingImages =
            AiutaOnboardingImages(
                onboardingTryOnMainImage1 = AiutaResourceImage(Res.drawable.onboarding_main_1),
                onboardingTryOnMainImage2 = AiutaResourceImage(Res.drawable.onboarding_main_2),
                onboardingTryOnMainImage3 = AiutaResourceImage(Res.drawable.onboarding_main_3),
                onboardingTryOnItemImage1 = AiutaResourceImage(Res.drawable.onboarding_item_1),
                onboardingTryOnItemImage2 = AiutaResourceImage(Res.drawable.onboarding_item_2),
                onboardingTryOnItemImage3 = AiutaResourceImage(Res.drawable.onboarding_item_3),
                onboardingBestResulBadImage1 =
                    AiutaResourceImage(
                        Res.drawable.onboarding_bad_image_1,
                    ),
                onboardingBestResulBadImage2 =
                    AiutaResourceImage(
                        Res.drawable.onboarding_bad_image_2,
                    ),
                onboardingBestResulGoodImage1 =
                    AiutaResourceImage(
                        Res.drawable.onboarding_good_image_1,
                    ),
                onboardingBestResulGoodImage2 =
                    AiutaResourceImage(
                        Res.drawable.onboarding_good_image_2,
                    ),
            ),
        selectorEmptySmallImage1 = AiutaResourceImage(Res.drawable.selector_empty_small_image_1),
        selectorEmptySmallImage2 = AiutaResourceImage(Res.drawable.onboarding_good_image_1),
        feedbackThanksImage = AiutaResourceImage(Res.drawable.ic_heart),
    )
}

@Composable
public fun rememberDefaultAiutaImages(): AiutaImages {
    return remember { defaultAiutaImages() }
}
