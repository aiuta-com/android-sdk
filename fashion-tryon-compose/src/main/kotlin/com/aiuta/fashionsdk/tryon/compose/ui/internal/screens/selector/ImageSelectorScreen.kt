package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.content.ImageSelectorScreenContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorAutoTryOnListener

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    sendPageEvent(pageId = AiutaAnalyticPageId.IMAGE_PICKER)

    ImageSelectorAutoTryOnListener()

    ImageSelectorScreenContent(modifier = modifier)
}
