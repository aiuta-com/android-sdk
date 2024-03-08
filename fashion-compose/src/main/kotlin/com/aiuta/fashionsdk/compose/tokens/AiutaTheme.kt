package com.aiuta.fashionsdk.compose.tokens

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

// TODO Docs
@Immutable
public interface AiutaTheme {
    @get:DrawableRes
    public val navLogo: Int

    public val colors: AiutaColors
}

public fun defaultAiutaTheme(
    navLogo: Int? = null,
    colors: AiutaColors? = null,
): AiutaTheme {
    return object : AiutaTheme {
        override val navLogo: Int = navLogo ?: FashionIcon.MainLogo
        override val colors: AiutaColors = colors ?: defaultAiutaColors()
    }
}
