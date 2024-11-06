package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.molecules.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Composable
internal fun DefaultImage(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    AiutaImage(
        modifier = modifier,
        image = theme.images.selectorEmptyImage,
        contentDescription = null,
    )
}
