package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.content.ImageSelectorScreenContent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorAutoTryOnListener

@Composable
internal fun ImageSelectorScreen(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val generationStatus = controller.generationStatus

    val isLastSavedPhotoAvailable = controller.isLastSavedPhotoAvailable()
    val isTryOnButtonVisible =
        remember {
            derivedStateOf {
                generationStatus.value != SKUGenerationUIStatus.LOADING && isLastSavedPhotoAvailable.value
            }
        }

    sendPageEvent(pageId = AiutaAnalyticPageId.IMAGE_PICKER)

    ImageSelectorAutoTryOnListener()

    ImageSelectorScreenContent(
        modifier = modifier,
    )

//    Column(
//        modifier = modifier.background(theme.colors.background),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        MainAppBar(
//            modifier =
//                Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//        )
//
//        Spacer(Modifier.height(16.dp))
//
//        ImageSelectorBlock(
//            modifier = Modifier.fillMaxSize(MAIN_IMAGE_SIZE),
//            uploadPhoto = {
//                controller.bottomSheetNavigator.show(
//                    newSheetScreen =
//                        NavigationBottomSheetScreen.ImagePicker(
//                            originPageId = AiutaAnalyticPageId.IMAGE_PICKER,
//                        ),
//                )
//            },
//        )
//
//        ImageSelectorFooter(
//            modifier =
//                Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(),
//            isTryOnButtonVisible = isTryOnButtonVisible,
//        )
//    }
}
