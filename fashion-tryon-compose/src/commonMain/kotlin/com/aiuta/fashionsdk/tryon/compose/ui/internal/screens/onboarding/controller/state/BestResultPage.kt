package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPageFeature

internal class BestResultPage(
    bestResultsPageFeature: AiutaOnboardingBestResultsPageFeature,
) : OnboardingState {
    override val pageTitle: String? = bestResultsPageFeature.strings.onboardingBestResultsPageTitle

    val goodImages: List<AiutaDrawableResource> = bestResultsPageFeature.images.onboardingBestResultsGood
    val badImages: List<AiutaDrawableResource> = bestResultsPageFeature.images.onboardingBestResultsBad
}
