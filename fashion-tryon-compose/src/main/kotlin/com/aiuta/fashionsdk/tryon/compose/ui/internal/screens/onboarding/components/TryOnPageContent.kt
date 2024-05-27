package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.OnboardingController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.TryOnPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.changeInternalTryOnPage

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
internal fun TryOnPageContent(
    modifier: Modifier = Modifier,
    onboardingController: OnboardingController,
) {
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

    Box(
        modifier = modifier.padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        currentPageTransition.Crossfade(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.85f),
        ) { page ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(page?.mainImage),
                contentDescription = null,
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

@Composable
private fun ItemContent(
    modifier: Modifier = Modifier,
    itemImage: Int,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current

    val widthTransition =
        animateDpAsState(
            targetValue = if (isActive) 88.dp else 64.dp,
            label = "widthTransition",
        )

    val heightTransition =
        animateDpAsState(
            targetValue = if (isActive) 120.dp else 88.dp,
            label = "heightTransition",
        )

    val cornerRadiusTransition =
        animateDpAsState(
            targetValue = if (isActive) 12.dp else 10.dp,
            label = "cornerRadiusTransition",
        )

    Image(
        modifier =
            modifier
                .width(widthTransition.value)
                .height(heightTransition.value)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(cornerRadiusTransition.value),
                    ambientColor = Color.Black,
                    spotColor = Color.Black,
                )
                .background(
                    color = theme.colors.background,
                    shape = RoundedCornerShape(cornerRadiusTransition.value),
                )
                .clickableUnindicated {
                    onClick()
                },
        painter = painterResource(itemImage),
        contentDescription = null,
    )
}
