package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.appbar.MainAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendOpenResultsScreenEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.GenerationResultBody
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.DisclaimerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.GenerationResultFooter
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.disclaimerHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.imagesHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.rememberGenerationResultController

@Composable
internal fun GenerationResultScreen(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    sendOpenResultsScreenEvent()

    GenerationResultListener()

    Column(
        modifier = modifier.background(theme.colors.background),
    ) {
        MainAppBar(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))

        GenerationResultScreenContent(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun GenerationResultScreenContent(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val generationResultController = rememberGenerationResultController(maxHeight = maxHeight)

        GenerationResultBody(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .height(generationResultController.imagesHeight(maxHeight))
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
            generationResultController = generationResultController,
        )

        DisclaimerBlock(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = generationResultController.imagesHeight(maxHeight))
                    .height(generationResultController.disclaimerHeight(maxHeight))
                    .fillMaxWidth(),
        )

        GenerationResultFooter(
            generationResultController = generationResultController,
        )
    }
}

// @OptIn(ExperimentalFoundationApi::class)
// @Composable
// internal fun GenerationResultScreen(modifier: Modifier = Modifier) {
//    val theme = LocalTheme.current
//    val controller = LocalController.current
//
//    sendOpenResultsScreenEvent()
//
//    GenerationResultListener()
//
//    BoxWithConstraints(
//        modifier = modifier.background(theme.colors.background),
//    ) {
//        val constraintsScope = this
//        val maxHeight = constraintsScope.maxHeight
//
//        // Controllers
//        val generationResultController =
//            rememberGenerationResultController(
//                maxHeight = maxHeight,
//            )
//        val connection =
//            rememberGenerationResultNestedScroll(
//                generationResultController = generationResultController,
//            )
//
//        // Height calculations
//        val bodyFooterPadding = 16.dp
//        val expandedFooterOffset =
//            with(generationResultController) {
//                maxHeight * (bodyWeight / totalWeight) + bodyFooterPadding
//            }
//        val expandedFooterOffsetPx = with(LocalDensity.current) { expandedFooterOffset.toPx() }
//
//        Column(
//            modifier =
//                Modifier
//                    .fillMaxSize()
//                    .requiredHeight(maxHeight + expandedFooterOffset)
//                    .offset {
//                        // Offset of swipe and minus centring of requiredHeight() modifier
//                        IntOffset(
//                            x = 0,
//                            y = (generationResultController.verticalSwipeState.requireOffset() + (expandedFooterOffsetPx / 2)).roundToInt(),
//                        )
//                    }
//                    .anchoredDraggable(
//                        state = generationResultController.verticalSwipeState,
//                        orientation = Orientation.Vertical,
//                        enabled = controller.isActiveSKUGenerateMoreNotEmpty().value,
//                    )
//                    .nestedScroll(connection),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            DisclaimerBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexList)
//                        .fillMaxWidth(),
//            )
//
//            GenerationVerticalPagerBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexList)
//                        .fillMaxWidth()
//                        .height(generationResultController.bodyHeight(maxHeight)),
//                generationResultController = generationResultController,
//            )
//
//            GenerationMoreBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexList)
//                        .fillMaxWidth()
//                        .height(maxHeight),
//                generationResultController = generationResultController,
//            )
//        }
//
//        GenerationResultInterface(
//            modifier = Modifier.fillMaxSize(),
//            generationResultController = generationResultController,
//        )
//    }
// }

// @Composable
// internal fun GenerationResultInterface(
//    modifier: Modifier = Modifier,
//    generationResultController: GenerationResultController,
// ) {
//    val theme = LocalTheme.current
//
//    AnimatedVisibility(
//        modifier = modifier,
//        visible = generationResultController.isInterfaceVisible.value,
//        enter = fadeIn(),
//        exit = fadeOut(),
//    ) {
//        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
//            GenerationButtonsBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexInterface)
//                        .fillMaxWidth()
//                        .heightIn(min = generationResultController.footerHeight(maxHeight))
//                        .background(theme.colors.backgroundElevation2)
//                        .align(Alignment.BottomCenter)
//                        .windowInsetsPadding(WindowInsets.navigationBars)
//                        .padding(horizontal = 16.dp),
//            )
//
//            GenerationCarouselBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexInterface)
//                        .height(generationResultController.bodyHeight(maxHeight))
//                        .padding(start = 16.dp)
//                        .align(Alignment.TopStart),
//                generationResultController = generationResultController,
//            )
//
//            ThanksFeedbackBlock(
//                modifier =
//                    Modifier
//                        .zIndex(generationResultController.zIndexInterface)
//                        .align(Alignment.Center),
//                generationResultController = generationResultController,
//            )
//        }
//    }
// }
