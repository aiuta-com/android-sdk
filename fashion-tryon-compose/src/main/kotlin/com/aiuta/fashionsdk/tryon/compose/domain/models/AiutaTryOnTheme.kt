package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.compose.tokens.defaultAiutaTheme

@Immutable
public interface AiutaTryOnTheme {
    @get:DrawableRes
    public val navLogo: Int?

    public val colors: AiutaTryOnColors?
}

public fun defaultAiutaTryOnTheme(
    navLogo: Int? = null,
    colors: AiutaTryOnColors? = null,
): AiutaTryOnTheme {
    return object : AiutaTryOnTheme {
        override val navLogo: Int? = navLogo
        override val colors: AiutaTryOnColors? = colors
    }
}

internal fun AiutaTryOnTheme?.toTheme(): AiutaTheme {
    return this?.let {
        defaultAiutaTheme(
            navLogo = navLogo,
            colors = colors?.toColors(),
        )
    } ?: defaultAiutaTheme()
}
