package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

internal class BestResultPage(
    bestResultsPageFeature: AiutaOnboardingBestResultsPageFeature,
) : OnboardingState {
    override val pageTitle: String? = bestResultsPageFeature.strings.onboardingBestResultsPageTitle

    val goodImages: List<AiutaDrawableResource> = bestResultsPageFeature.images.onboardingBestResultsGood
    val badImages: List<AiutaDrawableResource> = bestResultsPageFeature.images.onboardingBestResultsBad
}
