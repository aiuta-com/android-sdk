package com.aiuta.fashionsdk.configuration.ui.theme.pagebar

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.icons.AiutaPageBarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.toggles.AiutaPageBarThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.typography.AiutaPageBarThemeTypography

@Immutable
public class AiutaPageBarTheme private constructor(
    public val typography: AiutaPageBarThemeTypography,
    public val icons: AiutaPageBarThemeIcons,
    public val toggles: AiutaPageBarThemeToggles,
) {
    public class Builder {
        public var typography: AiutaPageBarThemeTypography? = null
        public var icons: AiutaPageBarThemeIcons? = null
        public var toggles: AiutaPageBarThemeToggles? = null

        public fun build(): AiutaPageBarTheme {
            val parentClass = "AiutaPageBarTheme"

            return AiutaPageBarTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.pageBar(
    block: AiutaPageBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    pageBar = AiutaPageBarTheme.Builder().apply(block).build()
}
