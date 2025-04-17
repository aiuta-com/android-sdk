package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.rememberAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.rememberAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.deletingGeneratedImagesListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.deletingUploadedImagesListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.rememberAiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.rememberFashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun NavigationInitialisation(
    modifier: Modifier = Modifier,
    aiutaConfiguration: AiutaConfiguration,
    productItem: ProductItem,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val controller = rememberFashionTryOnController(
            aiutaTryOnConfiguration = aiutaConfiguration.tryOnConfiguration,
            aiutaUserInterfaceConfiguration = aiutaConfiguration.userInterface,
            productItem = productItem,
        )

        CompositionLocalProvider(
            LocalAnalytic provides controller.analytic,
            LocalController provides controller,
            LocalTheme provides aiutaConfiguration.userInterface.theme,
            LocalAiutaConfiguration provides aiutaConfiguration.tryOnConfiguration,
            LocalAiutaTryOnDataController provides rememberAiutaTryOnDataController(
                aiuta = { aiutaConfiguration.tryOnConfiguration.aiuta },
            ),
            LocalAiutaTryOnDialogController provides rememberAiutaTryOnDialogController(),
            LocalAiutaTryOnLoadingActionsController provides rememberAiutaTryOnLoadingActionsController(),
        ) {
            // Init listeners
            val loadingActionsController = LocalAiutaTryOnLoadingActionsController.current
            loadingActionsController.deletingGeneratedImagesListener()
            loadingActionsController.deletingUploadedImagesListener(controller)

            // Actual content
            content()
        }
    }
}
