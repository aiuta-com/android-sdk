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
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.OnboardingState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOnboardingController(): OnboardingController {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val configuration = LocalAiutaConfiguration.current

    val onboardingStatesQueue =
        remember {
            if (configuration.toggles.isExtendedConsentPageAvailable) {
                listOf(
                    TryOnPage(theme.images),
                    ConsentPage,
                )
            } else {
                listOf(TryOnPage(theme.images))
            }
        }

    val pagerState =
        rememberPagerState(
            pageCount = {
                TryOnPage(theme.images).internalPages.size + onboardingStatesQueue.size - 1
            },
        )
    val scope = rememberCoroutineScope()

    return remember {
        OnboardingController(
            supplementPoint = stringResources.onboardingPageConsentSupplementaryPoints,
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
