package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.tryon

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components.common.CentredTextBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.changeInternalTryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state.TryOnPage

@Composable
internal fun TryOnPageContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
    val stringResources = LocalAiutaTryOnStringResources.current
    val currentPage =
        remember(onboardingController.pagerState.settledPage) {
            derivedStateOf {
                TryOnPage.INTERNAL_PAGES.getOrNull(onboardingController.pagerState.settledPage)
            }
        }

    val currentPageTransition =
        updateTransition(
            targetState = currentPage.value,
            label = "currentPageTransition",
        )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        ImagesBlock(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .weight(0.7f)
                    .padding(horizontal = 20.dp),
            currentPageTransition = currentPageTransition,
            onboardingController = onboardingController,
        )

        CentredTextBlock(
            modifier = Modifier.weight(0.3f),
            title = stringResources.onboardingPageTryonTopic,
            subtitle = stringResources.onboardingPageTryonSubtopic,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ImagesBlock(
    modifier: Modifier = Modifier,
    currentPageTransition: Transition<TryOnPage.InternalPage?>,
    onboardingController: OnboardingController,
) {
    val theme = LocalTheme.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        currentPageTransition.Crossfade(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.85f),
        ) { page ->
            Image(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(theme.shapes.mainImage),
                painter = rememberAsyncImagePainter(page?.mainImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TryOnPage.INTERNAL_PAGES.forEachIndexed { index, page ->
                key(page.uniqueGeneratedId) {
                    ItemContent(
                        itemImage = page.itemImage,
                        isActive = index == onboardingController.pagerState.settledPage,
                        onClick = {
                            onboardingController.changeInternalTryOnPage(index)
                        },
                    )
                }
            }
        }
    }
}
