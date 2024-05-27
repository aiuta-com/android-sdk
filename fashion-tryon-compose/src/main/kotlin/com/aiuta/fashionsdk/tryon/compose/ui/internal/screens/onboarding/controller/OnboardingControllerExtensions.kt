package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic.sendContinueOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic.sendFinishOnBoardingEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
internal fun OnboardingController.nextPage(
    controller: FashionTryOnController,
    stringResources: InternalAiutaTryOnLanguage,
) {
    val nextPageIndex = pagerState.settledPage + 1

    when (state.value) {
        is TryOnPage -> {
            val isLastPageOfTryOn = pagerState.settledPage == TryOnPage.INTERNAL_PAGES.lastIndex

            if (isLastPageOfTryOn) {
                // Change state to next
                state.value =
                    BestResultPage(
                        topic = stringResources.onboardingPageBestResultTopic,
                        subtopic = stringResources.onboardingPageBestResultSubtopic,
                    )
            }

            controller.sendContinueOnBoardingEvent(newPage = nextPageIndex)
        }

        is BestResultPage -> {
            scope.launch {
                controller.onboardingInteractor.setOnboardingAsFinished()
                controller.sendFinishOnBoardingEvent()
                controller.navigateTo(NavigationScreen.IMAGE_SELECTOR)
            }
        }
    }

    scope.launch {
        pagerState.animateScrollToPage(nextPageIndex)
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal fun OnboardingController.changeInternalTryOnPage(newPage: Int) {
    scope.launch {
        pagerState.animateScrollToPage(newPage)
    }
}
