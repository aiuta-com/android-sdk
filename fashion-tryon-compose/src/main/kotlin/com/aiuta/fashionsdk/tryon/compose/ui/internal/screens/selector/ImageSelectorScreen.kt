package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendOpenMainScreenEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body.ImageSelectorBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.common.ImageSelectorAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer.ImageSelectorFooter
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorListener

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageSelectorAppBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
        )

        Spacer(Modifier.height(24.dp))

        ImageSelectorBlock(
            modifier = Modifier.fillMaxSize(0.75f),
            uploadPhoto = {
                controller.bottomSheetNavigator.show(NavigationBottomSheetScreen.ImagePicker)
            },
        )

        ImageSelectorFooter(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            isTryOnButtonVisible = isTryOnButtonVisible,
        )

//        Box(
//            modifier =
//                Modifier
//                    .weight(2f)
//                    .fillMaxWidth()
//                    .windowInsetsPadding(WindowInsets.navigationBars),
//            contentAlignment = Alignment.Center,
//        ) {
//            AiutaLabel(modifier = Modifier.align(Alignment.Center))
//
//            androidx.compose.animation.AnimatedVisibility(
//                modifier =
//                    Modifier
//                        .fillMaxHeight()
//                        .fillMaxWidth()
//                        .background(theme.colors.background),
//                visible = isTryOnButtonVisible.value,
//                enter = fadeIn() + expandVertically(),
//                exit = fadeOut() + shrinkVertically(),
//            ) {
//                Box(
//                    modifier =
//                        Modifier
//                            .fillMaxSize()
//                            .clickableUnindicated {
//                                // Nothing, intercept
//                            },
//                    contentAlignment = Alignment.Center,
//                ) {
//                    FashionButton(
//                        modifier =
//                            Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp),
//                        text = stringResources.tryOn,
//                        style = FashionButtonStyles.primaryStyle(theme),
//                        size = FashionButtonSizes.lSize(),
//                        iconRes = FashionIcon.Magic,
//                        onClick = {
//                            controller.startGeneration(
//                                aiutaConfiguration = aiutaConfiguration,
//                                origin = StartUITryOn.Origin.TRY_ON_BUTTON,
//                            )
//                        },
//                    )
//                }
//            }
//        }
    }
}
