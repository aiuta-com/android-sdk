package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImages

internal class BestResultPage(
    aiutaImages: AiutaImages,
) : OnboardingState {
    val goodImages: List<AiutaImage> =
        listOf(
            aiutaImages.onboardingImages.onboardingBestResulGoodImage1,
            aiutaImages.onboardingImages.onboardingBestResulGoodImage2,
        )

    val badImages: List<AiutaImage> =
        listOf(
            aiutaImages.onboardingImages.onboardingBestResulBadImage1,
            aiutaImages.onboardingImages.onboardingBestResulBadImage2,
        )
}
