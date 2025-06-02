package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.content.ImageSelectorScreenContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorAutoTryOnListener

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    sendPageEvent(pageId = AiutaAnalyticsPageId.IMAGE_PICKER)

    ImageSelectorAutoTryOnListener()

    ImageSelectorScreenContent(modifier = modifier)
}
