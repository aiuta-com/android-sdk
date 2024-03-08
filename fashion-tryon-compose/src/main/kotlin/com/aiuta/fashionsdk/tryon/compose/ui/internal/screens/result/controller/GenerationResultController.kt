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
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

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

    val skuGenerationStatus =
        controller
            .fashionTryOn()
            .skuGenerationStatus
            .collectAsStateWithLifecycle()

    // Size of generation + meta sku images
    val generationSize = { skuGenerationStatus.value.imageUrls.size }
    val totalPages = { generationSize() + 1 }
    val pagerState = rememberPagerState(pageCount = totalPages)

    val imageCarouselState = rememberLazyListState()

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
                            ) { -maxHeight.toPx() }
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
            imageCarouselState = imageCarouselState,
            verticalSwipeState = verticalSwipeState,
        )
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
    // Carousel state
    public val imageCarouselState: LazyListState,
    // Swipe state
    public val verticalSwipeState: AnchoredDraggableState<GenerateResultState>,
)

internal fun GenerationResultController.bodyHeight(maxHeight: Dp) =
    maxHeight * (bodyWeight / totalWeight)

internal fun GenerationResultController.footerHeight(maxHeight: Dp) =
    maxHeight * (footerWeight / totalWeight)

internal fun GenerationResultController.isGenerationPagerItem(index: Int) =
    index < generationPageSize()

internal fun GenerationResultController.isMetaInfoPagerItem(index: Int) =
    index == totalPageSize() - 1
