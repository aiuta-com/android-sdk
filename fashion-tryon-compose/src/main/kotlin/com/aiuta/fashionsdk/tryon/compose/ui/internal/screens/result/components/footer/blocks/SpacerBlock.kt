package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN

internal fun LazyGridScope.spacerBlock(
    index: Int,
    height: Dp,
) {
    item(
        key = "SPACER_BLOCK_KEY_$index",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "SPACER_BLOCK_KEY__TYPE",
    ) {
        Spacer(Modifier.height(height))
    }
}
