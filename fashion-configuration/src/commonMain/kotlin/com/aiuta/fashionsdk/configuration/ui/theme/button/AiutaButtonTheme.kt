package com.aiuta.fashionsdk.configuration.ui.theme.button

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.button.shapes.AiutaButtonThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.button.typography.AiutaButtonThemeTypography

@Immutable
public class AiutaButtonTheme private constructor(
    public val typography: AiutaButtonThemeTypography,
    public val shapes: AiutaButtonThemeShapes,
) {
    public class Builder {
        public var typography: AiutaButtonThemeTypography? = null
        public var shapes: AiutaButtonThemeShapes? = null

        public fun build(): AiutaButtonTheme {
            val parentClass = "AiutaButtonTheme"

            return AiutaButtonTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.button(
    block: AiutaButtonTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    button = AiutaButtonTheme.Builder().apply(block).build()
}
