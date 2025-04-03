package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.common.spacerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.description.itemDescriptionBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.photos.itemPhotosBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController

internal const val FOOTER_FULL_SIZE_SPAN = 2

@Composable
internal fun GenerationResultFooterList(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val density = LocalDensity.current
    val statusBarsPx = WindowInsets.statusBars.getTop(density)
    val statusBars = with(density) { statusBarsPx.toDp() }

    LazyVerticalGrid(
        modifier = modifier,
        state = generationResultController.footerListState,
        columns = GridCells.Fixed(FOOTER_FULL_SIZE_SPAN),
    ) {
        spacerBlock(index = 0, height = 20.dp)

        itemDescriptionBlock(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        spacerBlock(index = 1, height = 32.dp)

        itemPhotosBlock(
            modifier = Modifier.fillMaxWidth(),
            generationResultController = generationResultController,
        )

        spacerBlock(index = 2, height = statusBars)
    }
}
