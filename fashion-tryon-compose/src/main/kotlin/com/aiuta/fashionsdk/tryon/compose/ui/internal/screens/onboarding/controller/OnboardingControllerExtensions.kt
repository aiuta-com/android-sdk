package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
internal fun OnboardingController.nextPage(controller: FashionTryOnController) {
    when (state.value) {
        is TryOnPage -> {
            val isLastPageOfTryOn = pagerState.settledPage == TryOnPage.INTERNAL_PAGES.lastIndex

            if (isLastPageOfTryOn) {
                // Change state to next
                state.value = BestResultPage
            }
        }

        is BestResultPage -> {
            scope.launch {
                controller.onboardingInteractor.setOnboardingAsFinished()
                controller.navigateTo(NavigationScreen.IMAGE_SELECTOR)
            }
        }
    }

    scope.launch {
        val nextPageIndex = pagerState.settledPage + 1
        pagerState.animateScrollToPage(nextPageIndex)
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal fun OnboardingController.changeInternalTryOnPage(newPage: Int) {
    scope.launch {
        pagerState.animateScrollToPage(newPage)
    }
}