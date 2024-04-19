package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.AiutaNavBarTheme
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.compose.tokens.defaultAiutaTheme

/**
 * Theme for Digital Try On flow.
 * Depends on provided [navBarTheme] and [colors] final
 * theme will be override
 */
@Immutable
public interface AiutaTryOnTheme {
    public val navBarTheme: AiutaNavBarTheme?

    public val colors: AiutaTryOnColors?

    @get:DrawableRes
    public val watermarkRes: Int?
}

public fun defaultAiutaTryOnTheme(
    navBarTheme: AiutaNavBarTheme? = null,
    colors: AiutaTryOnColors? = null,
    @DrawableRes watermarkRes: Int? = null,
): AiutaTryOnTheme {
    return object : AiutaTryOnTheme {
        override val navBarTheme: AiutaNavBarTheme? = navBarTheme
        override val colors: AiutaTryOnColors? = colors
        override val watermarkRes: Int? = watermarkRes
    }
}

internal fun AiutaTryOnTheme?.toTheme(): AiutaTheme {
    return this?.let {
        defaultAiutaTheme(
            navBarTheme = navBarTheme,
            colors = colors?.toColors(),
            watermarkRes = watermarkRes,
        )
    } ?: defaultAiutaTheme()
}
