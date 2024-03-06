package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.GenerationButtonsBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.GenerationCarouselBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.GenerationMoreBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.GenerationVerticalPagerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.connection.rememberGenerationResultNestedScroll
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.bodyHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.footerHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.rememberGenerationResultController
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationResultScreen(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier.background(FashionColor.White),
    ) {
        val constraintsScope = this
        val maxHeight = constraintsScope.maxHeight

        // Controllers
        val generationResultController =
            rememberGenerationResultController(
                maxHeight = maxHeight,
            )
        val connection =
            rememberGenerationResultNestedScroll(
                generationResultController = generationResultController,
            )

        // Height calculations
        val bodyFooterPadding = 16.dp
        val expandedFooterOffset =
            with(generationResultController) {
                maxHeight * (bodyWeight / totalWeight) + bodyFooterPadding
            }
        val expandedFooterOffsetPx = with(LocalDensity.current) { expandedFooterOffset.toPx() }

        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .requiredHeight(maxHeight + expandedFooterOffset)
                .offset {
                    // Offset of swipe and minus centring of requiredHeight() modifier
                    IntOffset(
                        x = 0,
                        y = (generationResultController.verticalSwipeState.requireOffset() + (expandedFooterOffsetPx / 2)).roundToInt(),
                    )
                }
                .anchoredDraggable(
                    state = generationResultController.verticalSwipeState,
                    orientation = Orientation.Vertical,
                )
                .nestedScroll(connection),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GenerationVerticalPagerBlock(
                modifier =
                Modifier
                    .zIndex(generationResultController.zIndexList)
                    .fillMaxWidth()
                    .height(generationResultController.bodyHeight(maxHeight)),
                generationResultController = generationResultController,
            )

            GenerationMoreBlock(
                modifier = Modifier
                    .zIndex(generationResultController.zIndexList)
                    .fillMaxWidth()
            )
        }

        GenerationResultInterface(
            generationResultController = generationResultController,
        )
    }
}

@Composable
internal fun BoxWithConstraintsScope.GenerationResultInterface(
    generationResultController: GenerationResultController,
) {
    GenerationButtonsBlock(
        modifier =
        Modifier
            .zIndex(generationResultController.zIndexInterface)
            .fillMaxWidth()
            .height(generationResultController.footerHeight(maxHeight))
            .background(FashionColor.White)
            .align(Alignment.BottomCenter)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 16.dp),
    )

    GenerationCarouselBlock(
        modifier =
        Modifier
            .zIndex(generationResultController.zIndexInterface)
            .height(generationResultController.bodyHeight(maxHeight))
            .padding(start = 16.dp)
            .align(Alignment.TopStart),
        generationResultController = generationResultController,
    )
}
