package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.AiutaUserInterfaceConfiguration
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
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.coil.newImageLoader
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun NavigationInitialisation(
    modifier: Modifier = Modifier,
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaTryOnListeners: AiutaTryOnListeners,
    aiutaUserInterfaceConfiguration: AiutaUserInterfaceConfiguration,
    productItem: ProductItem,
    content: @Composable () -> Unit,
) {
    // Init coil
    val coilContext = LocalPlatformContext.current
    setSingletonImageLoaderFactory { newImageLoader(coilContext) }

    BoxWithConstraints(
        modifier = modifier,
    ) {
        val controller = rememberFashionTryOnController(
            aiutaTryOnConfiguration = aiutaTryOnConfiguration,
            aiutaTryOnListeners = aiutaTryOnListeners,
            productItem = productItem,
        )

        CompositionLocalProvider(
            LocalAnalytic provides controller.analytic,
            LocalController provides controller,
            LocalTheme provides aiutaUserInterfaceConfiguration.theme,
            LocalAiutaConfiguration provides aiutaTryOnConfiguration,
            LocalAiutaTryOnDataController provides
                rememberAiutaTryOnDataController(
                    aiuta = { aiutaTryOnConfiguration.aiuta },
                ),
            LocalAiutaTryOnDialogController provides rememberAiutaTryOnDialogController(),
            LocalAiutaTryOnLoadingActionsController provides rememberAiutaTryOnLoadingActionsController(),
        ) {
            // Init listeners
            val loadingActionsController = LocalAiutaTryOnLoadingActionsController.current
            loadingActionsController.deletingGeneratedImagesListener(controller)
            loadingActionsController.deletingUploadedImagesListener(controller)

            // Actual content
            content()
        }
    }
}
