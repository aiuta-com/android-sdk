package com.aiuta.fashionsdk.compose.tokens

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * Final theme for all UI flows
 */
@Immutable
public interface AiutaTheme {
    public val navBarTheme: AiutaNavBarTheme

    public val colors: AiutaColors

    @get:DrawableRes
    public val watermarkRes: Int?
}

public fun defaultAiutaTheme(
    navBarTheme: AiutaNavBarTheme? = null,
    colors: AiutaColors? = null,
    @DrawableRes watermarkRes: Int? = null,
): AiutaTheme {
    return object : AiutaTheme {
        override val navBarTheme: AiutaNavBarTheme = navBarTheme ?: defaultAiutaNavBarTheme()
        override val colors: AiutaColors = colors ?: defaultAiutaColors()
        override val watermarkRes: Int? = watermarkRes
    }
}
