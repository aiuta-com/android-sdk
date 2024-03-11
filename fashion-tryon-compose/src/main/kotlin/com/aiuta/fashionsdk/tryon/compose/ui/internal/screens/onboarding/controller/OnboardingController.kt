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
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun rememberOnboardingController(
    navigateTo: (NavigationScreen) -> Unit,
): OnboardingController {
    val defaultState =
        remember {
            mutableStateOf<OnboardingState>(TryOnPage)
        }

    // Try on pages + Best result page
    val pagerState =
        rememberPagerState(
            pageCount = {
                TryOnPage.INTERNAL_PAGES.size + 1
            },
        )
    val scope = rememberCoroutineScope()

    return remember {
        OnboardingController(
            state = defaultState,
            pagerState = pagerState,
            scope = scope,
            navigateTo = navigateTo,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Immutable
internal class OnboardingController(
    val state: MutableState<OnboardingState>,
    val pagerState: PagerState,
    internal val scope: CoroutineScope,
    internal val navigateTo: (NavigationScreen) -> Unit,
)
