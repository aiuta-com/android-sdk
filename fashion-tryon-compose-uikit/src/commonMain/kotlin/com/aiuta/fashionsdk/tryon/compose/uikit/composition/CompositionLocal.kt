package com.aiuta.fashionsdk.tryon.compose.uikit.composition

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme

// Theme
public val LocalTheme: ProvidableCompositionLocal<AiutaTheme> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalTheme")
    }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
