package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.models.ImageSelectorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.transitionAnimation
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

@Composable
internal fun ImageSelectorBottom(
    modifier: Modifier = Modifier,
    uploadPhoto: () -> Unit,
) {
    val controller = LocalController.current
    val fashionTryOn = remember { controller.fashionTryOn() }
    val skuGenerationStatus = fashionTryOn.skuGenerationStatus.collectAsStateWithLifecycle()
    val sharedModifier = Modifier.fillMaxWidth()

    val bottomState =
        updateTransition(
            targetState =
                when {
                    skuGenerationStatus.value is SKUGenerationStatus.LoadingGenerationStatus -> {
                        ImageSelectorState.GENERATION_LOADING
                    }

                    controller.isLastSavedPhotoAvailable().value -> {
                        ImageSelectorState.LAST_IMAGE_SAVED
                    }

                    else -> ImageSelectorState.IDLE
                },
            label = "bottomState",
        )

    bottomState.AnimatedContent(
        modifier = modifier,
        transitionSpec = { transitionAnimation },
    ) { state ->
        when (state) {
            ImageSelectorState.IDLE -> {
                FashionButton(
                    modifier = sharedModifier,
                    text = stringResource(R.string.image_selector_upload_button),
                    style = FashionButtonStyles.primaryStyle(),
                    size = FashionButtonSizes.xlSize(),
                    onClick = uploadPhoto,
                )
            }

            ImageSelectorState.LAST_IMAGE_SAVED -> {
                FashionButton(
                    modifier = sharedModifier,
                    text = stringResource(R.string.image_selector_change_button),
                    style = FashionButtonStyles.outlineStyle(),
                    size = FashionButtonSizes.xlSize(),
                    onClick = uploadPhoto,
                )
            }

            ImageSelectorState.GENERATION_LOADING -> {
                Row(
                    modifier = sharedModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = FashionColor.MediumGray,
                        strokeWidth = 2.dp,
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = stringResource(R.string.image_selector_generating_outfit),
                        style = MaterialTheme.typography.body1,
                        color = FashionColor.MediumGray,
                    )
                }
            }
        }
    }
}
