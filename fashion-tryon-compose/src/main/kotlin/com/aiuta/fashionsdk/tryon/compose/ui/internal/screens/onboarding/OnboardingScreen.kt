package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendStartOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.BestResultPageContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common.OnboardingAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.tryon.TryOnPageContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.nextPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.rememberOnboardingController

@Composable
internal fun OnboardingScreen(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val onboardingController = rememberOnboardingController()

    val generalHorizontalPadding = 16.dp

    if (!configuration.isPreOnboardingAvailable) {
        sendStartOnBoardingEvent()
    }

    Column(
        modifier =
            modifier
                .background(theme.colors.background)
                .windowInsetsPadding(WindowInsets.navigationBars),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OnboardingAppBar(
            modifier =
                Modifier
                    .padding(horizontal = generalHorizontalPadding)
                    .fillMaxWidth(),
        )

        OnboardingScreenContent(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
            onboardingController = onboardingController,
        )

        FashionButton(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = generalHorizontalPadding),
            text =
                if (onboardingController.state.value is TryOnPage) {
                    stringResources.onboardingButtonNext
                } else {
                    stringResources.onboardingButtonStart
                },
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                onboardingController.nextPage(
                    controller = controller,
                    stringResources = stringResources,
                )
            },
        )

        Spacer(Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val onboardingState =
        updateTransition(
            targetState = onboardingController.state.value,
            label = "onboardingState",
        )

    Box(
        modifier = modifier,
    ) {
        // Just for collect pager state
        VerticalPager(
            modifier = Modifier.alpha(0f),
            state = onboardingController.pagerState,
        ) {}

        onboardingState.AnimatedContent(
            modifier = modifier,
        ) { state ->
            when (state) {
                is TryOnPage -> {
                    TryOnPageContent(
                        modifier = Modifier.fillMaxSize(),
                        onboardingController = onboardingController,
                    )
                }

                is BestResultPage -> {
                    BestResultPageContent(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(32.dp),
                        state = state,
                    )
                }
            }
        }
    }
}
