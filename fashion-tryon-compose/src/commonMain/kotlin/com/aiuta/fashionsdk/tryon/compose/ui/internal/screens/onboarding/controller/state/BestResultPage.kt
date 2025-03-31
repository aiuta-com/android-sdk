package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.onboarding.bestresult.AiutaOnboardingBestResultsPage

internal class BestResultPage(
    bestResultsPageFeature: AiutaOnboardingBestResultsPage,
) : OnboardingState {
    override val pageTitle: String? = bestResultsPageFeature.strings.onboardingBestResultsPageTitle

    val goodImages: List<AiutaImage> = bestResultsPageFeature.images.onboardingBestResultsGood
    val badImages: List<AiutaImage> = bestResultsPageFeature.images.onboardingBestResultsBad
}
