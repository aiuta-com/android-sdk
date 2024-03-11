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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.indicator.PagerIndicator
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.BestResultPageContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.TryOnPageContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.BestResultPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.nextPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.rememberOnboardingController

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingScreen(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val onboardingController =
        rememberOnboardingController(
            navigateTo = controller::navigateTo,
        )

    Column(
        modifier =
            modifier
                .background(theme.colors.background)
                .padding(horizontal = 16.dp)
                .windowInsetsPadding(WindowInsets.navigationBars),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(onboardingController.state.value.topic),
            style = MaterialTheme.typography.h5,
            color = theme.colors.primary,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(onboardingController.state.value.subtopic),
            style = MaterialTheme.typography.h6,
            color = theme.colors.primary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(20.dp))

        OnboardingScreenContent(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
            onboardingController = onboardingController,
        )

        Spacer(Modifier.height(30.dp))

        PagerIndicator(
            pagerState = onboardingController.pagerState,
            color = theme.colors.brand,
        )

        Spacer(Modifier.height(24.dp))

        FashionButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.onboarding_button_next), // TODO
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                onboardingController.nextPage()
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
                        state = state,
                        onboardingController = onboardingController,
                    )
                }

                is BestResultPage -> {
                    BestResultPageContent(
                        modifier = Modifier.fillMaxSize().padding(32.dp),
                        state = state,
                    )
                }
            }
        }
    }
}
