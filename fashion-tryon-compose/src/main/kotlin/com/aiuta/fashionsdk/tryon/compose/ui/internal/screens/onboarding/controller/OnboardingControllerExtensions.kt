package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticOnboardingEventType
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.SupplementaryConsent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendOnboardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.popUpAndNavigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import kotlinx.coroutines.launch

internal fun OnboardingController.nextPage(
    controller: FashionTryOnController,
    configuration: AiutaTryOnConfiguration,
) {
    scope.launch {
        val nextPageIndex = pagerState.settledPage + 1
        val nextState =
            onboardingStatesQueue.getOrNull(
                index = (nextPageIndex - TryOnPage.INTERNAL_PAGES_LAST_INDEX).coerceAtLeast(0),
            )

        if (nextState != null) {
            val isLastPageOfTryOn = pagerState.settledPage == TryOnPage.INTERNAL_PAGES_LAST_INDEX

            // Skip for Try on page case
            if (state.value !is TryOnPage || isLastPageOfTryOn) {
                state.value = nextState
            }

            pagerState.animateScrollToPage(nextPageIndex)
        } else {
            val skuItem = controller.activeSKUItem.value

            // Close onboarding and move on
            controller.onboardingInteractor.setOnboardingAsFinished()

            // Consent
            val supplementaryConsents =
                supplementPointsMap.map { item ->
                    SupplementaryConsent(
                        consentText = item.key,
                        isObtained = item.value,
                    )
                }
            controller.sendOnboardingEvent(
                eventType = AiutaAnalyticOnboardingEventType.CONSENT_GIVEN,
                pageId = AiutaAnalyticPageId.CONSENT,
                productId = skuItem.skuId,
                supplementaryConsents = supplementaryConsents,
            )
            configuration.dataProvider?.obtainUserConsentAction?.invoke(supplementaryConsents)

            // Finish
            controller.sendOnboardingEvent(
                eventType = AiutaAnalyticOnboardingEventType.ONBOARDING_FINISHED,
                pageId = AiutaAnalyticPageId.CONSENT,
                productId = skuItem.skuId,
                supplementaryConsents = null,
            )
            controller.popUpAndNavigateTo(NavigationScreen.ImageSelector)
        }
    }
}

internal fun OnboardingController.previousPage(controller: FashionTryOnController) {
    scope.launch {
        val previousPageIndex = pagerState.settledPage - 1
        val isFirstPage = pagerState.settledPage == 0

        val previousState =
            onboardingStatesQueue.getOrNull(
                index = (previousPageIndex - TryOnPage.INTERNAL_PAGES_LAST_INDEX).coerceAtLeast(0),
            ).takeIf { !isFirstPage }

        if (previousState != null) {
            // Skip for Try on page case
            if (state.value !is TryOnPage) {
                state.value = previousState
            }

            pagerState.animateScrollToPage(previousPageIndex)
        } else {
            // Try to navigate back
            controller.navigateBack()
        }
    }
}

internal fun OnboardingController.changeInternalTryOnPage(newPage: Int) {
    scope.launch {
        pagerState.animateScrollToPage(newPage)
    }
}

// Agreement
internal fun OnboardingController.updateMandatoryAgreementState(newState: Boolean) {
    isMandatoryAgreementChecked.value = newState
}

internal fun OnboardingController.updateSupplementAgreementState(
    point: String,
    newState: Boolean,
) {
    supplementPointsMap[point] = newState
}

@Composable
internal fun OnboardingController.listenIsPrimaryButtonEnabled(): State<Boolean> {
    return remember(
        state.value,
        isMandatoryAgreementChecked.value,
    ) {
        mutableStateOf(state.value !is ConsentPage || isMandatoryAgreementChecked.value)
    }
}
