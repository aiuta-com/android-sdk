package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.imageSource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.size
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.images.ImagesContainer
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.PhotoLabel

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
        ) { uploadedImageUris ->
            if (uploadedImageUris.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    DefaultImage(
                        modifier = Modifier.fillMaxHeight(0.55f).fillMaxWidth(),
                    )
                }
            } else {
                ImagesContainer(
                    modifier = Modifier.fillMaxSize().clip(sharedCornerShape),
                    getImageUrls = { uploadedImageUris },
                )
            }
        }

        lastSavedPhotoUrisTransition.AnimatedVisibility(
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp),
            visible = { it.size > 1 },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            PhotoLabel(
                count = controller.lastSavedImages.value.size,
            )
        }

        skuGenerationTransition.AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = { it == SKUGenerationUIStatus.LOADING },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ShimmerBlock(modifier = Modifier.fillMaxSize())
        }
    }
}