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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendOpenMainScreenEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendTapChangePhotoEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.AiutaLabel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.ImageSelectorBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    val aiutaUri = stringResource(R.string.aiuta_url)
    val controller = LocalController.current
    val uriHandler = LocalUriHandler.current
    val theme = LocalTheme.current

    val generationStatus = controller.generationStatus

    val isLastSavedPhotoAvailable = controller.isLastSavedPhotoAvailable()
    val isTryOnButtonVisible =
        remember {
            derivedStateOf {
                generationStatus.value != SKUGenerationUIStatus.LOADING && isLastSavedPhotoAvailable.value
            }
        }

    sendOpenMainScreenEvent()

    ImageSelectorListener(enable = controller.isGenerationActive.value)

    Column(
        modifier = modifier.background(theme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        ImageSelectorBlock(
            modifier = Modifier.fillMaxSize(0.7f),
            uploadPhoto = {
                controller.sendTapChangePhotoEvent()
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
            AiutaLabel(
                onClick = {
                    uriHandler.openUri(aiutaUri)
                },
            )

            androidx.compose.animation.AnimatedVisibility(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .background(theme.colors.background)
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
                        style = FashionButtonStyles.primaryStyle(theme),
                        size = FashionButtonSizes.xlSize(),
                        iconRes = FashionIcon.Magic,
                        onClick = {
                            controller.startGeneration(
                                origin = StartUITryOn.Origin.TRY_ON_BUTTON,
                            )
                        },
                    )
                }
            }
        }
    }
}
