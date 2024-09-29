package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.tryon.compose.R

internal object BestResultPage : OnboardingState {
    val goodImages: List<Int> =
        listOf(
            R.drawable.onboarding_good_image_1,
            R.drawable.onboarding_good_image_2,
        )

    val badImages: List<Int> =
        listOf(
            R.drawable.onboarding_bad_image_1,
            R.drawable.onboarding_bad_image_2,
        )
}
