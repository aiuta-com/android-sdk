package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.LazyPagingItems
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack

@Composable
internal fun HistoryScreenListeners(generatedImages: LazyPagingItems<GeneratedImage>) {
    val controller = LocalController.current

    // If we have empty history, let's navigate back
    LaunchedEffect(generatedImages.itemCount) {
        if (generatedImages.itemCount == 0 && generatedImages.loadState.append.endOfPaginationReached) {
            controller.navigateBack()
        }
    }
}
