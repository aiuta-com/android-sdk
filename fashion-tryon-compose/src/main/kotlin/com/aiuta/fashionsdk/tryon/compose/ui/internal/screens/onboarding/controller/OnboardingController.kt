package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.ConsentPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.OnboardingState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOnboardingController(): OnboardingController {
    // Try on pages + Best result page
    val pagerState =
        rememberPagerState(
            pageCount = {
                TryOnPage.INTERNAL_PAGES.size + 2
            },
        )
    val scope = rememberCoroutineScope()

    return remember {
        OnboardingController(
            pagerState = pagerState,
            scope = scope,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Immutable
internal class OnboardingController(
    val pagerState: PagerState,
    internal val scope: CoroutineScope,
) {
    // Agreement
    val isAgreementChecked = mutableStateOf(false)

    // Onboarding queue
    val onboardingStatesQueue =
        listOf(
            TryOnPage,
            BestResultPage,
            ConsentPage,
        )

    // General state
    val state: MutableState<OnboardingState> = mutableStateOf(onboardingStatesQueue.first())
}
