package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
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

internal enum class GenerateResultState {
    SHOW_GENERATIONS,

    SHOW_GENERATE_MORE,
}

private val BODY_WEIGHT = 8.5f
private val FOOTER_WEIGHT = 1.5f
private val TOTAL_WEIGHT = BODY_WEIGHT + FOOTER_WEIGHT

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

    // Size of generation + meta sku images
    val generationSize = { generationUrlsSize.value }
    val totalPages = { generationSize() + 1 }
    val pagerState = rememberPagerState(pageCount = totalPages)

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
                positionalThreshold = { distance: Float -> distance * 0.5f },
                velocityThreshold = { with(density) { (bodyHeight / 2).toPx() } },
                animationSpec = tween(),
            ).apply {
                updateAnchors(
                    DraggableAnchors {
                        GenerateResultState.SHOW_GENERATIONS at 0f
                        GenerateResultState.SHOW_GENERATE_MORE at
                            with(
                                density,
                            ) { -maxHeight.toPx() + 48.dp.toPx() }
                    },
                )
            }
        }

    return remember {
        GenerationResultController(
            generationPagerState = pagerState,
            generationPageSize = generationSize,
            totalPageSize = totalPages,
            zIndexList = controller.zIndexInterface - 2,
            zIndexInterface = controller.zIndexInterface - 1,
            isInterfaceVisible = defaultInterfaceVisibility,
            imageCarouselState = imageCarouselState,
            verticalSwipeState = verticalSwipeState,
        )
    }.also {
        GenerationResultControllerListener(it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Immutable
internal class GenerationResultController(
    // General config
    public val bodyWeight: Float = BODY_WEIGHT,
    public val footerWeight: Float = FOOTER_WEIGHT,
    public val totalWeight: Float = TOTAL_WEIGHT,
    // Generation pager state
    public val generationPagerState: PagerState,
    public val generationPageSize: () -> Int,
    public val totalPageSize: () -> Int,
    public val zIndexList: Float,
    public val zIndexInterface: Float,
    // Interface visibility
    public val isInterfaceVisible: MutableState<Boolean>,
    // Carousel state
    public val imageCarouselState: LazyListState,
    // Swipe state
    public val verticalSwipeState: AnchoredDraggableState<GenerateResultState>,
)

// Size calculation
internal fun GenerationResultController.bodyHeight(maxHeight: Dp) =
    maxHeight * (bodyWeight / totalWeight)

internal fun GenerationResultController.footerHeight(maxHeight: Dp) =
    maxHeight * (footerWeight / totalWeight)

internal fun GenerationResultController.isGenerationPagerItem(index: Int) =
    index < generationPageSize()

internal fun GenerationResultController.isMetaInfoPagerItem(index: Int) =
    index == totalPageSize() - 1

// Interface visibility
internal fun GenerationResultController.showInterface() {
    isInterfaceVisible.value = true
}

internal fun GenerationResultController.hideInterface() {
    isInterfaceVisible.value = false
}
