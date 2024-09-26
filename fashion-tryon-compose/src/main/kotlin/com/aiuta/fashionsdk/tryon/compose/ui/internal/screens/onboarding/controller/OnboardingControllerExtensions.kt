package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic.sendContinueOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic.sendFinishOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
internal fun OnboardingController.nextPage(controller: FashionTryOnController) {
    val nextPageIndex = pagerState.settledPage + 1

    when (state.value) {
        is TryOnPage -> {
            val isLastPageOfTryOn = pagerState.settledPage == TryOnPage.INTERNAL_PAGES.lastIndex

            if (isLastPageOfTryOn) {
                // Change state to next
                state.value = BestResultPage
            }

            controller.sendContinueOnBoardingEvent(newPage = nextPageIndex)
        }

        is BestResultPage -> {
            state.value = ConsentPage
            controller.sendContinueOnBoardingEvent(newPage = nextPageIndex)
        }

        is ConsentPage -> {
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

internal fun OnboardingController.changeInternalTryOnPage(newPage: Int) {
    scope.launch {
        pagerState.animateScrollToPage(newPage)
    }
}

// Agreement
internal fun OnboardingController.updateAgreementState(newState: Boolean) {
    isAgreementChecked.value = newState
}

@Composable
internal fun OnboardingController.listenIsPrimaryButtonEnabled(): State<Boolean> {
    return remember(
        state.value,
        isAgreementChecked.value,
    ) {
        mutableStateOf(state.value !is ConsentPage || isAgreementChecked.value)
    }
}
