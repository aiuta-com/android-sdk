package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ImageSelectorPhoto(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val generationStatus = controller.generationStatus

    val sharedCornerShape = RoundedCornerShape(24.dp)

    // Animation
    val lastSavedPhotoUrisTransition =
        updateTransition(
            targetState = controller.lastSavedPhotoUris.value,
            label = "lastSavedPhotoUrisTransition",
        )

    val skuGenerationTransition =
        updateTransition(
            targetState = generationStatus.value,
            label = "skuGenerationTransition",
        )

    // Lottie
    val dynamicProperties =
        rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        theme.colors.brand.hashCode(),
                        BlendModeCompat.SRC_ATOP,
                    ),
                keyPath =
                    arrayOf(
                        "**",
                    ),
            ),
        )
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
                .clip(sharedCornerShape)
                .clipToBounds(),
        contentAlignment = Alignment.Center,
    ) {
        lastSavedPhotoUrisTransition.AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
        ) { uploadedImageUris ->
            val imageUri = uploadedImageUris.firstOrNull()

            if (imageUri == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    DefaultImage(
                        modifier = Modifier.fillMaxHeight(0.55f).fillMaxWidth(),
                    )
                }
            } else {
                UploadImage(
                    modifier = Modifier.fillMaxSize().clip(sharedCornerShape),
                    imageUri = imageUri,
                )
            }
        }

        skuGenerationTransition.AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = { it == SKUGenerationUIStatus.LOADING },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LottieAnimation(
                modifier = Modifier.fillMaxSize().clip(sharedCornerShape),
                contentScale = ContentScale.FillHeight,
                composition = composition.value,
                progress = { progress.value },
                dynamicProperties = dynamicProperties,
            )
        }
    }
}
