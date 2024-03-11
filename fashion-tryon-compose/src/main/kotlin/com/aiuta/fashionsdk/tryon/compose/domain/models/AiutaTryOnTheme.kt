package com.aiuta.fashionsdk.tryon.compose.domain.models

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
}

public fun defaultAiutaTryOnTheme(
    navBarTheme: AiutaNavBarTheme? = null,
    colors: AiutaTryOnColors? = null,
): AiutaTryOnTheme {
    return object : AiutaTryOnTheme {
        override val navBarTheme: AiutaNavBarTheme? = navBarTheme
        override val colors: AiutaTryOnColors? = colors
    }
}

internal fun AiutaTryOnTheme?.toTheme(): AiutaTheme {
    return this?.let {
        defaultAiutaTheme(
            navBarTheme = navBarTheme,
            colors = colors?.toColors(),
        )
    } ?: defaultAiutaTheme()
}
