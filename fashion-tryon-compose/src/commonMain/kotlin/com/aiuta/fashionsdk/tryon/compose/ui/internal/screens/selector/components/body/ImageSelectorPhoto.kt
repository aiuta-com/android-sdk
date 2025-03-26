package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.imageSource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images.ImagesContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun ImageSelectorPhoto(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val generationStatus = controller.generationStatus

    val sharedCornerShape = theme.shapes.mainImage

    // Animation
    val lastSavedPhotoUrisTransition =
        updateTransition(
            targetState = controller.lastSavedImages.value.imageSource,
            label = "lastSavedPhotoUrisTransition",
        )

    val skuGenerationTransition =
        updateTransition(
            targetState = generationStatus.value,
            label = "skuGenerationTransition",
        )

    Box(
        modifier =
            modifier
                .clip(sharedCornerShape)
                .clipToBounds(),
        contentAlignment = Alignment.Center,
    ) {
        lastSavedPhotoUrisTransition.AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
        ) { images ->
            ImagesContainer(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(sharedCornerShape),
                getImages = { images },
            )
        }

        skuGenerationTransition.AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = { it == SKUGenerationUIStatus.LOADING },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            sendPageEvent(pageId = AiutaAnalyticPageId.LOADING)
            ShimmerBlock(modifier = Modifier.fillMaxSize())
        }
    }
}
