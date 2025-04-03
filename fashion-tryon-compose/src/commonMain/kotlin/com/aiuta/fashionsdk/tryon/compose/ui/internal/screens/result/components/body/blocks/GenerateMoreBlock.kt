package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerateMoreListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

@Composable
internal fun GenerateMoreBlock(modifier: Modifier = Modifier) {
    val tryOnFeature = strictProvideFeature<AiutaTryOnFeature>()

    if (tryOnFeature.toggles.isRepickingAllowed) {
        val controller = LocalController.current
        val theme = LocalTheme.current

        val activeSKUItem = controller.activeSKUItem.value
        val countGeneratedOperation =
            controller.generatedOperationInteractor
                .countGeneratedOperation()
                .collectAsState(0)

        GenerateMoreListener()

        IconButton(
            modifier = modifier,
            icon = theme.icons.camera24,
            onClick = {
                controller.sendResultEvent(
                    event = AiutaAnalyticsResultsEventType.PICK_OTHER_PHOTO,
                    pageId = AiutaAnalyticPageId.RESULTS,
                    productId = activeSKUItem.skuId,
                )

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
            },
        )
    }
}
