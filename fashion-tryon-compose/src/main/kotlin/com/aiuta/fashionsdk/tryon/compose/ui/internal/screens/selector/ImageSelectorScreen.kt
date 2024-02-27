package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector

import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.ImageSelectorBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    val aiutaUri = stringResource(R.string.aiuta_url)
    val controller = LocalController.current
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()

    val fashionTryOn = remember { controller.fashionTryOn() }
    val skuGenerationStatus = fashionTryOn.skuGenerationStatus.collectAsStateWithLifecycle()

    val isLastSavedPhotoAvailable = controller.isLastSavedPhotoAvailable()
    val isTryOnButtonVisible =
        remember {
            derivedStateOf {
                skuGenerationStatus.value !is SKUGenerationStatus.LoadingGenerationStatus && isLastSavedPhotoAvailable.value
            }
        }

    ImageSelectorListener(enable = controller.isGenerationActive.value)

    Column(
        modifier = modifier.background(FashionColor.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        ImageSelectorBlock(
            modifier = Modifier.fillMaxSize(0.7f),
            uploadPhoto = {
                controller.bottomSheetNavigator.show(NavigationBottomSheetScreen.ImagePicker)
            },
        )

        Box(
            modifier =
                Modifier
                    .weight(2f)
                    .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier =
                    Modifier
                        .clickableUnindicated {
                            uriHandler.openUri(aiutaUri)
                        },
                text = stringResource(R.string.image_selector_powered_by_aiuta),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.body1,
                color = FashionColor.MediumGray,
            )

            androidx.compose.animation.AnimatedVisibility(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .background(FashionColor.White)
                        .align(Alignment.BottomCenter),
                visible = isTryOnButtonVisible.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .clickableUnindicated {
                                // Nothing, intercept
                            },
                    contentAlignment = Alignment.Center,
                ) {
                    FashionButton(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .windowInsetsPadding(WindowInsets.navigationBars),
                        text = stringResource(R.string.try_on),
                        style = FashionButtonStyles.primaryStyle(),
                        size = FashionButtonSizes.xlSize(),
                        iconRes = FashionIcon.Magic,
                        onClick = {
                            controller.startGeneration(scope = scope)
                        },
                    )
                }
            }
        }
    }
}
