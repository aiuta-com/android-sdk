package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations

@Deprecated("Need to delete")
internal enum class GenerateResultState {
    SHOW_GENERATIONS,

    SHOW_GENERATE_MORE,
}

internal enum class GenerateResultStatus {
    MORE_COLLAPSED,

    MORE_EXPANDED,
}

@Deprecated("Need to delete")
private val BODY_WEIGHT = 8.5f

@Deprecated("Need to delete")
private val FOOTER_WEIGHT = 1.5f

@Deprecated("Need to delete")
private val TOTAL_WEIGHT = BODY_WEIGHT + FOOTER_WEIGHT

private val imagesWeight: Float = 0.75f
private val totalWeight: Float = 1f

private val disclaimerContentHeight: Dp = 30.dp
private val disclaimerBottomPaddingHeight: Dp = 12.dp
private val disclaimerTotalHeight: Dp = disclaimerContentHeight + disclaimerBottomPaddingHeight

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun rememberGenerationResultController(maxHeight: Dp): GenerationResultController {
    val density = LocalDensity.current
    val controller = LocalController.current

    val successGenerationOperations = controller.subscribeToSuccessOperations()
    val generationUrlsSize =
        remember(successGenerationOperations.value) {
            derivedStateOf {
                successGenerationOperations.value.flatMap { it.generatedImageUrls }.size
            }
        }

    val generateMoreSKU = controller.activeSKUItem.value.generateMoreSKU.orEmpty()

    // Size of generation + meta sku images
    val generationSize = { generationUrlsSize.value }
    val totalPages = { generationSize() + 1 }
    val pagerState = rememberPagerState(pageCount = { generationUrlsSize.value })

    val imageCarouselState = rememberLazyListState()
    val defaultInterfaceVisibility =
        remember {
            mutableStateOf(true)
        }

    val bodyHeight = maxHeight * (BODY_WEIGHT / TOTAL_WEIGHT)
    val verticalSwipeState =
        remember {
            AnchoredDraggableState(
                initialValue = GenerateResultState.SHOW_GENERATIONS,
                anchors =
                    DraggableAnchors {
                        GenerateResultState.SHOW_GENERATIONS at 0f

                        // Show generate more only if it is not empty
                        if (generateMoreSKU.isNotEmpty()) {
                            GenerateResultState.SHOW_GENERATE_MORE at
                                with(
                                    density,
                                ) { -maxHeight.toPx() + 48.dp.toPx() }
                        }
                    },
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { (bodyHeight / 2).toPx() } },
                snapAnimationSpec = tween(),
                decayAnimationSpec = exponentialDecay(),
            )
        }

    val imagesHeight = maxHeight * imagesWeight

    val statusBarPx = WindowInsets.statusBars.getTop(density).toFloat()
    val footerOffset = imagesHeight + disclaimerContentHeight
    val footerOffsetPx = with(density) { footerOffset.toPx() }

    val verticalSwipeStateV2 =
        remember {
            AnchoredDraggableState(
                initialValue = GenerateResultStatus.MORE_COLLAPSED,
                anchors =
                    DraggableAnchors {
                        GenerateResultStatus.MORE_COLLAPSED at footerOffsetPx
                        GenerateResultStatus.MORE_EXPANDED at statusBarPx
                    },
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { (imagesHeight / 2).toPx() } },
                snapAnimationSpec = tween(),
                decayAnimationSpec = exponentialDecay(),
            )
        }

    return remember {
        GenerationResultController(
            generationPagerState = pagerState,
            generationPageSize = generationSize,
            totalPageSize = totalPages,
            isInterfaceVisible = defaultInterfaceVisibility,
            imageCarouselState = imageCarouselState,
            verticalSwipeState = verticalSwipeState,
            verticalSwipeStateV2 = verticalSwipeStateV2,
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
    @Deprecated("Need to delete")
    public val generationPageSize: () -> Int,
    @Deprecated("Need to delete")
    public val totalPageSize: () -> Int,
    // Interface visibility
    @Deprecated("Need to delete")
    public val isInterfaceVisible: MutableState<Boolean>,
    // Carousel state
    @Deprecated("Need to delete")
    public val imageCarouselState: LazyListState,
    // Swipe state
    @Deprecated("Need to delete")
    public val verticalSwipeState: AnchoredDraggableState<GenerateResultState>,
    public val verticalSwipeStateV2: AnchoredDraggableState<GenerateResultStatus>,
) {
    public val isThanksFeedbackBlockVisible = mutableStateOf(false)
}

// Size calculation
@Deprecated("Need to delete")
internal fun GenerationResultController.isGenerationPagerItem(index: Int) =
    index < generationPageSize()

@Deprecated("Need to delete")
internal fun GenerationResultController.isMetaInfoPagerItem(index: Int) =
    index == totalPageSize() - 1

internal fun GenerationResultController.imagesHeight(maxHeight: Dp): Dp {
    return maxHeight * (imagesWeight / totalWeight)
}

internal fun GenerationResultController.disclaimerHeight(maxHeight: Dp): Dp {
    return disclaimerTotalHeight
}

internal fun GenerationResultController.footerHeight(maxHeight: Dp): Dp {
    val rawFooterWeight = totalWeight - imagesWeight
    val rawFooterHeight = maxHeight * rawFooterWeight

    return rawFooterHeight - disclaimerContentHeight
}

// Interface visibility
@Deprecated("Need to delete")
internal fun GenerationResultController.showInterface() {
    isInterfaceVisible.value = true
}

@Deprecated("Need to delete")
internal fun GenerationResultController.hideInterface() {
    isInterfaceVisible.value = false
}
