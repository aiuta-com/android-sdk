package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.AiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.loading.AiutaTryOnLoadingActionsController

// Analytic
internal val LocalAnalytic: ProvidableCompositionLocal<InternalAiutaAnalytic> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAnalytic")
    }

// Configurationn
internal val LocalAiutaFeatures: ProvidableCompositionLocal<AiutaFeatures> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAiutaConfiguration")
    }

// Controllers
// Data
internal val LocalAiutaTryOnDataController: ProvidableCompositionLocal<AiutaTryOnDataController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("AiutaTryOnDataController")
    }

internal val LocalAiutaTryOnLoadingActionsController: ProvidableCompositionLocal<AiutaTryOnLoadingActionsController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("AiutaTryOnLoadingActionsController")
    }

// UI
internal val LocalAiutaTryOnDialogController: ProvidableCompositionLocal<AiutaTryOnDialogController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("AiutaTryOnDialogController")
    }

// Base controller
internal val LocalController: ProvidableCompositionLocal<FashionTryOnController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalController")
    }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
