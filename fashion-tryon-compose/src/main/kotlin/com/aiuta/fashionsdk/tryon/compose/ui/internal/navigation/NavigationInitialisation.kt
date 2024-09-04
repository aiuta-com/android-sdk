package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.compose.tokens.aiutaTheme
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.resolveInternalLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.defaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.rememberAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.rememberAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.rememberFashionTryOnController
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

@Composable
internal fun NavigationInitialisation(
    modifier: Modifier = Modifier,
    aiuta: () -> Aiuta,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    aiutaTryOnConfiguration: (() -> AiutaTryOnConfiguration)?,
    skuForGeneration: () -> SKUItem,
    theme: AiutaTheme? = null,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val internalAnalytic = remember { aiuta().internalAiutaAnalytic }
        val finalTheme = remember { theme ?: aiutaTheme() }
        val configuration =
            remember {
                aiutaTryOnConfiguration?.invoke() ?: defaultAiutaTryOnConfiguration()
            }
        val controller =
            rememberFashionTryOnController(
                analytic = { internalAnalytic },
                aiuta = aiuta,
                aiutaTryOn = aiutaTryOn,
                aiutaTryOnListeners = aiutaTryOnListeners,
                aiutaTryOnConfiguration = configuration,
                skuForGeneration = skuForGeneration,
            )

        CompositionLocalProvider(
            LocalAnalytic provides internalAnalytic,
            LocalController provides controller,
            LocalTheme provides finalTheme,
            LocalAiutaConfiguration provides configuration,
            LocalAiutaTryOnStringResources provides
                resolveInternalLanguage(
                    selectedLanguage = configuration.language,
                ),
            LocalAiutaTryOnDataController provides rememberAiutaTryOnDataController(aiuta),
            LocalAiutaTryOnDialogController provides rememberAiutaTryOnDialogController(),
        ) {
            content()
        }
    }
}
