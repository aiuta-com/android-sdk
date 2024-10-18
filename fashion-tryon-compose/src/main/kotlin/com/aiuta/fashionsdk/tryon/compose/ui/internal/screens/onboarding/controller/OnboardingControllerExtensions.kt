package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticOnboardingEventType
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendOnboardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic.sendContinueOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import kotlinx.coroutines.launch

internal fun OnboardingController.nextPage(controller: FashionTryOnController) {
    scope.launch {
        val nextPageIndex = pagerState.settledPage + 1
        val nextState =
            onboardingStatesQueue.getOrNull(
                index = (nextPageIndex - TryOnPage.INTERNAL_PAGES.lastIndex).coerceAtLeast(0),
            )

        if (nextState != null) {
            val isLastPageOfTryOn = pagerState.settledPage == TryOnPage.INTERNAL_PAGES.lastIndex

            // Skip for Try on page case
            if (state.value !is TryOnPage || isLastPageOfTryOn) {
                state.value = nextState
            }

            controller.sendContinueOnBoardingEvent(newPage = nextPageIndex)

            pagerState.animateScrollToPage(nextPageIndex)
        } else {
            // Close onboarding and move on
            controller.onboardingInteractor.setOnboardingAsFinished()
            controller.sendOnboardingEvent(AiutaAnalyticOnboardingEventType.CONSENT_GIVEN)
            controller.sendOnboardingEvent(AiutaAnalyticOnboardingEventType.ONBOARDING_FINISHED)
            controller.navigateTo(NavigationScreen.IMAGE_SELECTOR)
        }
    }
}

internal fun OnboardingController.previousPage(controller: FashionTryOnController) {
    scope.launch {
        val previousPageIndex = pagerState.settledPage - 1
        val isFirstPage = pagerState.settledPage == 0

        val previousState =
            onboardingStatesQueue.getOrNull(
                index = (previousPageIndex - TryOnPage.INTERNAL_PAGES.lastIndex).coerceAtLeast(0),
            ).takeIf { !isFirstPage }

        if (previousState != null) {
            // Skip for Try on page case
            if (state.value !is TryOnPage) {
                state.value = previousState
            }

            controller.sendContinueOnBoardingEvent(newPage = previousPageIndex)

            pagerState.animateScrollToPage(previousPageIndex)
        } else {
            // Ask for back stack from host
            controller.clickClose(
                origin = FinishSession.Origin.ONBOARDING_SCREEN,
                pageId =
                    when (state.value) {
                        is TryOnPage -> AiutaAnalyticPageId.HOW_IT_WORKS
                        is BestResultPage -> AiutaAnalyticPageId.BEST_RESULTS
                        is ConsentPage -> AiutaAnalyticPageId.CONSENT
                    },
            )
        }
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
