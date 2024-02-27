package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.utils.conditional
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.dashedBorder
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ImageSelectorPhoto(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val fashionTryOn = remember { controller.fashionTryOn() }
    val skuGenerationStatus = fashionTryOn.skuGenerationStatus.collectAsStateWithLifecycle()
    val sharedCornerShape = RoundedCornerShape(8.dp)

    // Animation
    val lastSavedPhotoUrisTransition =
        updateTransition(
            targetState = controller.lastSavedPhotoUris.value,
            label = "lastSavedPhotoUrisTransition",
        )

    val skuGenerationTransition =
        updateTransition(
            targetState = skuGenerationStatus.value,
            label = "skuGenerationTransition",
        )

    // Lottie
    val composition =
        rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scanning_animation))
    val progress =
        animateLottieCompositionAsState(
            composition = composition.value,
            iterations = LottieConstants.IterateForever,
        )

    Box(
        modifier =
            modifier
                .conditional(controller.lastSavedPhotoUris.value.isEmpty()) {
                    dashedBorder(
                        color = FashionColor.DarkGray,
                        shape = sharedCornerShape,
                        dashWidth = 8.dp,
                        gapWidth = 8.dp,
                    )
                }
                .background(
                    color = FashionColor.Black.copy(0.04f),
                    shape = sharedCornerShape,
                )
                .clipToBounds(),
        contentAlignment = Alignment.Center,
    ) {
        lastSavedPhotoUrisTransition.AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
        ) { uploadedImageUris ->
            val imageUri = uploadedImageUris.firstOrNull()

            if (imageUri == null) {
                DefaultImage(modifier = Modifier.fillMaxSize())
            } else {
                UploadImage(
                    modifier = Modifier.fillMaxSize().clip(sharedCornerShape),
                    imageUri = imageUri,
                )
            }
        }

        skuGenerationTransition.AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = { it is SKUGenerationStatus.LoadingGenerationStatus },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LottieAnimation(
                modifier = Modifier.fillMaxSize().clip(sharedCornerShape),
                contentScale = ContentScale.FillHeight,
                composition = composition.value,
                progress = { progress.value },
            )
        }
    }
}
