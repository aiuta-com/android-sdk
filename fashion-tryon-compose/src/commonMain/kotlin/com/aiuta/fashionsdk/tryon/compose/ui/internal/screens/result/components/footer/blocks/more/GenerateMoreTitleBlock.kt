package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN

internal fun LazyGridScope.generateMoreTitleBlock(modifier: Modifier = Modifier) {
    item(
        key = "GENERATE_MORE_TITLE_KEY",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "GENERATE_MORE_TITLE_TYPE",
    ) {
        GenerateMoreTitleBlock(modifier = modifier)
    }
}

@Composable
private fun GenerateMoreTitleBlock(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResources.generationResultMoreTitle,
            style = theme.typography.brandName,
            color = theme.colors.secondary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResources.generationResultMoreSubtitle,
            style = theme.typography.titleM,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )
    }
}
