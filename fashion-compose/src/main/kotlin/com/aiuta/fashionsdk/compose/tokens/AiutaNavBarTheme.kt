package com.aiuta.fashionsdk.compose.tokens

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

@Deprecated("Should delete in new release")
public interface AiutaNavBarTheme {
    /**
     * A small image of your logo to embed in the navigation bar.
     */
    @get:DrawableRes
    public val navLogo: Int

    /**
     * The color of navigation bar elements, such as the back button or text color
     */
    public val foregroundColor: Color?
}

@Deprecated("Should delete in new release")
public fun defaultAiutaNavBarTheme(
    navLogo: Int? = null,
    foregroundColor: Color? = null,
): AiutaNavBarTheme {
    return object : AiutaNavBarTheme {
        override val navLogo: Int = navLogo ?: FashionIcon.AiutaLogo
        override val foregroundColor: Color? = foregroundColor
    }
}
