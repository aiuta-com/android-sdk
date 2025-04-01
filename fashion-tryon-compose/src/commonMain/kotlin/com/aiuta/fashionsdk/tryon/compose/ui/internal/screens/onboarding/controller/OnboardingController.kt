package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.AiutaConsentStandaloneOnboardingPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.OnboardingState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.consent.consentFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.onboarding.strictOnboardingFeature
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOnboardingController(): OnboardingController {
    val consentFeature = consentFeature()
    val onboardingFeature = strictOnboardingFeature()

    val onboardingStatesQueue =
        remember {
            val rawOnboardingQueue = mutableListOf<OnboardingState>()

            // Try on page
            rawOnboardingQueue.add(TryOnPage(onboardingFeature.tryOnPage))

            // Best result
            onboardingFeature.bestResultsPage?.let { bestResultsPageFeature ->
                rawOnboardingQueue.add(BestResultPage(bestResultsPageFeature))
            }

            // Consent
            if (consentFeature is AiutaConsentStandaloneOnboardingPage) {
                rawOnboardingQueue.add(ConsentPage(consentFeature))
            }

            rawOnboardingQueue
        }

    val pagerState =
        rememberPagerState(
            pageCount = { onboardingStatesQueue.sumOf { it.pageSize() } },
        )
    val scope = rememberCoroutineScope()

    return remember {
        OnboardingController(
            supplementPoint =
                (consentFeature as? AiutaConsentStandaloneOnboardingPage)
                    ?.strings
                    ?.optionalConsentsHtml
                    .orEmpty(),
            onboardingStatesQueue = onboardingStatesQueue,
            pagerState = pagerState,
            scope = scope,
        )
    }
}

@Immutable
internal class OnboardingController(
    supplementPoint: List<String>,
    val onboardingStatesQueue: List<OnboardingState>,
    val pagerState: PagerState,
    internal val scope: CoroutineScope,
) {
    // Agreement
    val isMandatoryAgreementChecked = mutableStateOf(false)
    val supplementPointsMap =
        mutableStateMapOf<String, Boolean>(
            *supplementPoint.map {
                it to false
            }.toTypedArray(),
        )

    // General state
    val state: MutableState<OnboardingState> = mutableStateOf(onboardingStatesQueue.first())
}
