package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerateMoreListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendTapChangePhotoEvent

@Composable
internal fun GenerateMoreBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val isGenerateMoreFlowActive = remember { mutableStateOf(false) }

    GenerateMoreListener(isActive = isGenerateMoreFlowActive)

    IconButton(
        modifier = modifier,
        icon = theme.icons.camera24,
        onClick = {
            controller.sendTapChangePhotoEvent()
            controller.bottomSheetNavigator.show(
                newSheetScreen =
                    NavigationBottomSheetScreen.ImagePicker(
                        originPageId = AiutaAnalyticPageId.RESULTS,
                    ),
            )
            isGenerateMoreFlowActive.value = true
        },
    )
}
