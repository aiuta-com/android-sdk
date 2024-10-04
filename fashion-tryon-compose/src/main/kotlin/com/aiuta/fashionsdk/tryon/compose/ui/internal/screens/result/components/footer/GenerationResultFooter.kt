package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.common.dividerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.common.spacerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.description.itemDescriptionBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more.generateMoreListBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more.generateMoreTitleBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.photos.itemPhotosBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.connection.rememberGenerationResultConnection
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import kotlin.math.roundToInt

internal const val FOOTER_FULL_SIZE_SPAN = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BoxWithConstraintsScope.GenerationResultFooter(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val theme = LocalTheme.current

    val connection =
        rememberGenerationResultConnection(
            verticalSwipeState = generationResultController.verticalSwipeStateV2,
        )

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        0,
                        (generationResultController.verticalSwipeStateV2.offset).roundToInt(),
                    )
                }
                .background(
                    color = theme.colors.background,
                    shape = theme.shapes.bottomSheet,
                )
                .anchoredDraggable(
                    state = generationResultController.verticalSwipeStateV2,
                    orientation = Orientation.Vertical,
                )
                .nestedScroll(connection),
    ) {
        GenerationResultFooterList(
            modifier =
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.navigationBars),
            generationResultController = generationResultController,
        )
    }
}

@Composable
private fun GenerationResultFooterList(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val density = LocalDensity.current
    val statusBars = WindowInsets.statusBars.getTop(density).dp

    val activeSKUItem = controller.activeSKUItem.value

    LazyVerticalGrid(
        modifier = modifier,
        state = generationResultController.footerListState,
        columns = GridCells.Fixed(FOOTER_FULL_SIZE_SPAN),
    ) {
        spacerBlock(index = 0, height = 12.dp)

        itemDescriptionBlock(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
        )

        spacerBlock(index = 1, height = 32.dp)

        itemPhotosBlock(modifier = Modifier.fillMaxWidth())

        if (activeSKUItem.generateMoreSKU?.isNotEmpty() == true) {
            spacerBlock(index = 2, height = 32.dp)

            dividerBlock()

            spacerBlock(index = 3, height = 32.dp)

            generateMoreTitleBlock(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
            )

            spacerBlock(index = 4, height = 32.dp)

            generateMoreListBlock(skuItem = activeSKUItem)
        }

        spacerBlock(index = 5, height = statusBars)
    }
}
