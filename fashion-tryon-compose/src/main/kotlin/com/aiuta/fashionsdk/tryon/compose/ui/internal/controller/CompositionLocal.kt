package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

internal val LocalController: ProvidableCompositionLocal<FashionTryOnController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalController")
    }

internal val LocalTheme: ProvidableCompositionLocal<AiutaTheme> =
    staticCompositionLocalOf {
        noLocalProvidedFor("AiutaTheme")
    }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
