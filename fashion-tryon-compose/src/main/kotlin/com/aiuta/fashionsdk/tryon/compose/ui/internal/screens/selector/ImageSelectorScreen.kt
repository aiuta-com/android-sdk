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
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.appbar.MainAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.isLastSavedPhotoAvailable
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.body.ImageSelectorBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer.ImageSelectorFooter
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller.ImageSelectorAutoTryOnListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.MAIN_IMAGE_SIZE

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

    Column(
        modifier = modifier.background(theme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MainAppBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
        )

        Spacer(Modifier.height(16.dp))

        ImageSelectorBlock(
            modifier = Modifier.fillMaxSize(MAIN_IMAGE_SIZE),
            uploadPhoto = {
                controller.bottomSheetNavigator.show(
                    newSheetScreen =
                        NavigationBottomSheetScreen.ImagePicker(
                            originPageId = AiutaAnalyticPageId.IMAGE_PICKER,
                        ),
                )
            },
        )

        ImageSelectorFooter(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            isTryOnButtonVisible = isTryOnButtonVisible,
        )
    }
}
