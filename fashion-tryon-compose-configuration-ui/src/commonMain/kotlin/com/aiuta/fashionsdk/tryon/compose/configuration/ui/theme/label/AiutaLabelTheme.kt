package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.label

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.label.typography.AiutaLabelThemeTypography

@Immutable
public class AiutaLabelTheme private constructor(
    public val typography: AiutaLabelThemeTypography,
) {
    public class Builder {
        public var typography: AiutaLabelThemeTypography? = null

        public fun build(): AiutaLabelTheme {
            val parentClass = "AiutaLabelTheme"

            return AiutaLabelTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.label(
    block: AiutaLabelTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    label = AiutaLabelTheme.Builder().apply(block).build()
}
