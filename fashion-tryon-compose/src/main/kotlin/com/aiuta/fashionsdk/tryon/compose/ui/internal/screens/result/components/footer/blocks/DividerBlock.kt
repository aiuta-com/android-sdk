package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Divider
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN

internal fun LazyGridScope.dividerBlock() {
    item(
        key = "DIVIDER_BLOCK_KEY",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "DIVIDER_BLOCK_TYPE",
    ) {
        val theme = LocalTheme.current

        Divider(
            thickness = 4.dp,
            color = theme.colors.neutral,
        )
    }
}
