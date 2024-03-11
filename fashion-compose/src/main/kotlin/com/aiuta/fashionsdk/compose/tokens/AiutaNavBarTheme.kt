package com.aiuta.fashionsdk.compose.tokens

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

public interface AiutaNavBarTheme {
    @get:DrawableRes
    public val navLogo: Int

    public val foregroundColor: Color?
}

public fun defaultAiutaNavBarTheme(
    navLogo: Int? = null,
    foregroundColor: Color? = null,
): AiutaNavBarTheme {
    return object : AiutaNavBarTheme {
        override val navLogo: Int = navLogo ?: FashionIcon.AiutaLogo
        override val foregroundColor: Color? = foregroundColor
    }
}
