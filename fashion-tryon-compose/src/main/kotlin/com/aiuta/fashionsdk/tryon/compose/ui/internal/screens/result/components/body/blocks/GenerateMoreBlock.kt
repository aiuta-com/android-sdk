package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerateMoreListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendTapChangePhotoEvent

@Composable
internal fun GenerateMoreBlock(
    modifier: Modifier = Modifier,
    pageOffset: State<Float>,
) {
    val isVisible =
        remember {
            derivedStateOf {
                pageOffset.value == 0f
            }
        }

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        GenerateMoreBlockContent()
    }
}

@Composable
internal fun GenerateMoreBlockContent(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val activeSKUItem = controller.activeSKUItem.value
    val isGenerateMoreFlowActive = remember { mutableStateOf(false) }

    val countGeneratedOperation =
        controller.generatedOperationInteractor
            .countGeneratedOperation()
            .collectAsStateWithLifecycle(0)

    GenerateMoreListener(isActive = isGenerateMoreFlowActive)

    IconButton(
        modifier = modifier,
        icon = theme.icons.camera24,
        onClick = {
            controller.sendResultEvent(
                event = AiutaAnalyticsResultsEventType.PICK_OTHER_PHOTO,
                productId = activeSKUItem.skuId,
            )
            controller.sendTapChangePhotoEvent()

            controller.bottomSheetNavigator.show(
                newSheetScreen =
                    if (countGeneratedOperation.value > 1) {
                        NavigationBottomSheetScreen.GeneratedOperations
                    } else {
                        NavigationBottomSheetScreen.ImagePicker(
                            originPageId = AiutaAnalyticPageId.RESULTS,
                        )
                    },
            )
            isGenerateMoreFlowActive.value = true
        },
    )
}
