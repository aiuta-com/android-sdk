package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations

internal enum class GenerateResultStatus {
    MORE_COLLAPSED,

    MORE_EXPANDED,
}

// Weights
private const val APPBAR_WEIGHT: Float = 0.1f
private const val IMAGES_WEIGHT: Float = 0.7f
private const val TOTAL_WEIGHT: Float = 1f

private val disclaimerContentHeight: Dp = 30.dp
private val disclaimerBottomPaddingHeight: Dp = 12.dp
private val disclaimerTotalHeight: Dp = disclaimerContentHeight + disclaimerBottomPaddingHeight

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun rememberGenerationResultController(maxHeight: Dp): GenerationResultController {
    val density = LocalDensity.current
    val controller = LocalController.current
    val configuration = LocalConfiguration.current

    val successGenerationOperations = controller.subscribeToSuccessOperations()
    val generationUrlsSize =
        remember(successGenerationOperations.value) {
            derivedStateOf {
                successGenerationOperations.value.flatMap { it.generatedImageUrls }.size
            }
        }

    val pagerState = rememberPagerState(pageCount = { generationUrlsSize.value })

    val screenHeight = configuration.screenHeightDp.dp
    val screenHeightPx = with(density) { screenHeight.toPx() }

    val statusBarPx = WindowInsets.statusBars.getTop(density).toFloat()
    val footerHeightPx = remember { mutableFloatStateOf(0f) }
    val footerOffset = footerOffset(maxHeight)
    val footerOffsetPx = with(density) { footerOffset.toPx() }

    val verticalSwipeState =
        remember {
            AnchoredDraggableState(
                initialValue = GenerateResultStatus.MORE_COLLAPSED,
                anchors =
                    DraggableAnchors {
                        GenerateResultStatus.MORE_COLLAPSED at footerOffsetPx
                        GenerateResultStatus.MORE_EXPANDED at statusBarPx
                    },
                positionalThreshold = { distance: Float -> distance * 0.7f },
                velocityThreshold = { with(density) { (maxHeight / 2).toPx() } },
                snapAnimationSpec =
                    tween(
                        durationMillis = 1000,
                        easing = LinearOutSlowInEasing,
                    ),
                decayAnimationSpec = exponentialDecay(),
            )
        }

    LaunchedEffect(footerHeightPx.floatValue) {
        verticalSwipeState.updateAnchors(
            newAnchors =
                DraggableAnchors {
                    GenerateResultStatus.MORE_COLLAPSED at footerOffsetPx
                    GenerateResultStatus.MORE_EXPANDED at
                        (screenHeightPx - footerHeightPx.floatValue).coerceAtLeast(
                            statusBarPx,
                        )
                },
        )
    }

    val footerListState = rememberLazyGridState()

    return remember(density, configuration) {
        GenerationResultController(
            generationPagerState = pagerState,
            verticalSwipeState = verticalSwipeState,
            footerListState = footerListState,
            footerHeightPx = footerHeightPx,
        )
    }.also {
        GenerationResultControllerListener(it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Immutable
internal class GenerationResultController(
    // Generation pager state
    public val generationPagerState: PagerState,
    // Swipe state
    public val verticalSwipeState: AnchoredDraggableState<GenerateResultStatus>,
    public val footerListState: LazyGridState,
    public val footerHeightPx: MutableFloatState,
) {
    public val isThanksFeedbackBlockVisible = mutableStateOf(false)
}

// Size calculation
internal fun appbarHeight(maxHeight: Dp): Dp {
    return maxHeight * (APPBAR_WEIGHT / TOTAL_WEIGHT)
}

internal fun imagesHeight(maxHeight: Dp): Dp {
    return maxHeight * (IMAGES_WEIGHT / TOTAL_WEIGHT)
}

internal fun disclaimerHeight(): Dp {
    return disclaimerTotalHeight
}

internal fun disclaimerOffset(maxHeight: Dp): Dp {
    return appbarHeight(maxHeight) + imagesHeight(maxHeight)
}

internal fun footerOffset(maxHeight: Dp): Dp {
    return appbarHeight(maxHeight) + imagesHeight(maxHeight) + disclaimerContentHeight
}
