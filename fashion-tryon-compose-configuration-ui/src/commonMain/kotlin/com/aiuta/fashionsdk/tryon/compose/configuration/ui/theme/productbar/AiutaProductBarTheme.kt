package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.productbar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.productbar.colors.AiutaProductBarThemeColors
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

@Immutable
public class AiutaProductBarTheme private constructor(
    public val typography: AiutaProductBarThemeTypography,
    public val colors: AiutaProductBarThemeColors,
) {
    public class Builder {
        public var typography: AiutaProductBarThemeTypography? = null
        public var colors: AiutaProductBarThemeColors? = null

        public fun build(): AiutaProductBarTheme {
            val parentClass = "AiutaProductBarTheme"

            return AiutaProductBarTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                colors = colors.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "colors",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.image(
    block: AiutaProductBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    productBar = AiutaProductBarTheme.Builder().apply(block).build()
}
