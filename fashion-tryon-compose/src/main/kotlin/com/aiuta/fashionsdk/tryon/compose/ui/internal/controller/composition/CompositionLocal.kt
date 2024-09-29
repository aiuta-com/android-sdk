package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.AiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogController

// Analytic
internal val LocalAnalytic: ProvidableCompositionLocal<InternalAiutaAnalytic> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAnalytic")
    }

// Configuration
internal val LocalAiutaConfiguration: ProvidableCompositionLocal<AiutaTryOnConfiguration> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAiutaConfiguration")
    }

internal val LocalAiutaTryOnStringResources: ProvidableCompositionLocal<InternalAiutaTryOnLanguage> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAiutaTryOnStringResources")
    }

// Controllers
// Data
internal val LocalAiutaTryOnDataController: ProvidableCompositionLocal<AiutaTryOnDataController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("AiutaTryOnDataController")
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
