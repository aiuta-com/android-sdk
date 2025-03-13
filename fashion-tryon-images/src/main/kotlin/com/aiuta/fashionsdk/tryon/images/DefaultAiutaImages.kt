package com.aiuta.fashionsdk.tryon.images

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
                onboardingTryOnMainImage1 = AiutaResourceImage(R.drawable.onboarding_main_1),
                onboardingTryOnMainImage2 = AiutaResourceImage(R.drawable.onboarding_main_2),
                onboardingTryOnMainImage3 = AiutaResourceImage(R.drawable.onboarding_main_3),
                onboardingTryOnItemImage1 = AiutaResourceImage(R.drawable.onboarding_item_1),
                onboardingTryOnItemImage2 = AiutaResourceImage(R.drawable.onboarding_item_2),
                onboardingTryOnItemImage3 = AiutaResourceImage(R.drawable.onboarding_item_3),
                onboardingBestResulBadImage1 =
                    AiutaResourceImage(
                        R.drawable.onboarding_bad_image_1,
                    ),
                onboardingBestResulBadImage2 =
                    AiutaResourceImage(
                        R.drawable.onboarding_bad_image_2,
                    ),
                onboardingBestResulGoodImage1 =
                    AiutaResourceImage(
                        R.drawable.onboarding_good_image_1,
                    ),
                onboardingBestResulGoodImage2 =
                    AiutaResourceImage(
                        R.drawable.onboarding_good_image_2,
                    ),
            ),
        selectorEmptySmallImage1 = AiutaResourceImage(R.drawable.selector_empty_small_image_1),
        selectorEmptySmallImage2 = AiutaResourceImage(R.drawable.onboarding_good_image_1),
        feedbackThanksImage = AiutaResourceImage(R.drawable.ic_heart),
    )
}

@Composable
public fun rememberDefaultAiutaImages(): AiutaImages {
    return remember { defaultAiutaImages() }
}
