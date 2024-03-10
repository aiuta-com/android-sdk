package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.AiutaColors
import com.aiuta.fashionsdk.compose.tokens.defaultAiutaColors

/**
 * Colors for Digital Try On flow.
 * Will be used for creating final theme through [AiutaTryOnTheme]
 */
@Immutable
public interface AiutaTryOnColors {
    public val brand: Color?
    public val accent: Color?
    public val navLogoColor: Color?
}

public fun defaultAiutaTryOnColors(
    brand: Color? = null,
    accent: Color? = null,
    navLogoColor: Color? = null,
): AiutaTryOnColors {
    return object : AiutaTryOnColors {
        override val brand: Color? = brand
        override val accent: Color? = accent
        override val navLogoColor: Color? = navLogoColor
    }
}

internal fun AiutaTryOnColors.toColors(): AiutaColors {
    return defaultAiutaColors(
        brand = brand,
        accent = accent,
        navLogoColor = navLogoColor,
    )
}
