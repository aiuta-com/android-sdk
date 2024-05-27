package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration

internal val LocalAnalytic: ProvidableCompositionLocal<InternalAiutaAnalytic> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAnalytic")
    }

internal val LocalController: ProvidableCompositionLocal<FashionTryOnController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalController")
    }

internal val LocalTheme: ProvidableCompositionLocal<AiutaTheme> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalTheme")
    }

internal val LocalAiutaConfiguration: ProvidableCompositionLocal<AiutaTryOnConfiguration> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAiutaConfiguration")
    }

internal val LocalAiutaTryOnStringResources: ProvidableCompositionLocal<InternalAiutaTryOnLanguage> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalAiutaConfiguration")
    }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
