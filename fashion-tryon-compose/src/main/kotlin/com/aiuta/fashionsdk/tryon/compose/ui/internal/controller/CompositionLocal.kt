package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalController: ProvidableCompositionLocal<FashionTryOnController> =
    staticCompositionLocalOf {
        noLocalProvidedFor("LocalController")
    }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
