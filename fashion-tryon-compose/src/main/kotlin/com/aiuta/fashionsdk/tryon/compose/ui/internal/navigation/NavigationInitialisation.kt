package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.toTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.rememberFashionTryOnController
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

@Composable
internal fun NavigationInitialisation(
    modifier: Modifier = Modifier,
    aiuta: () -> Aiuta,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    theme: (() -> AiutaTryOnTheme)? = null,
    skuForGeneration: () -> SKUItem,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val internalAnalytic = remember { aiuta().internalAiutaAnalytic }
        val internalTheme = remember { theme?.invoke().toTheme() }
        val controller =
            rememberFashionTryOnController(
                analytic = { internalAnalytic },
                aiuta = aiuta,
                aiutaTryOn = aiutaTryOn,
                aiutaTryOnListeners = aiutaTryOnListeners,
                skuForGeneration = skuForGeneration,
            )

        CompositionLocalProvider(
            LocalAnalytic provides internalAnalytic,
            LocalController provides controller,
            LocalTheme provides internalTheme,
        ) {
            content()
        }
    }
}
