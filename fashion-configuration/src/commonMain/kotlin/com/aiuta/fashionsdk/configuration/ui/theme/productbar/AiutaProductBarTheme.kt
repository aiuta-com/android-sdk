package com.aiuta.fashionsdk.configuration.ui.theme.productbar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.colors.AiutaProductBarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.toggles.AiutaProductBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.typography.AiutaProductBarThemeTypography

@Immutable
public class AiutaProductBarTheme private constructor(
    public val typography: AiutaProductBarThemeTypography,
    public val colors: AiutaProductBarThemeColors,
    public val toggles: AiutaProductBarThemeToggles,
) {
    public class Builder {
        public var typography: AiutaProductBarThemeTypography? = null
        public var colors: AiutaProductBarThemeColors? = null
        public var toggles: AiutaProductBarThemeToggles? = null

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
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "toggles",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.productBar(
    block: AiutaProductBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    productBar = AiutaProductBarTheme.Builder().apply(block).build()
}
