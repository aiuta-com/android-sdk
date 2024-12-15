package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.resolveInternalLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.rememberAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.rememberAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.deletingGeneratedImagesListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.deletingUploadedImagesListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.rememberAiutaTryOnLoadingActionsController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.rememberFashionTryOnController

@Composable
internal fun NavigationInitialisation(
    modifier: Modifier = Modifier,
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaTheme: AiutaTheme,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val internalAnalytic =
            remember {
                aiutaTryOnConfiguration
                    .aiuta
                    .internalAiutaAnalytic
            }
        val controller =
            rememberFashionTryOnController(
                aiutaTryOnConfiguration = aiutaTryOnConfiguration,
            )

        CompositionLocalProvider(
            LocalAnalytic provides internalAnalytic,
            LocalController provides controller,
            LocalTheme provides aiutaTheme,
            LocalAiutaConfiguration provides aiutaTryOnConfiguration,
            LocalAiutaTryOnStringResources provides
                resolveInternalLanguage(
                    selectedLanguage = aiutaTryOnConfiguration.language,
                ),
            LocalAiutaTryOnDataController provides rememberAiutaTryOnDataController(aiuta),
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
