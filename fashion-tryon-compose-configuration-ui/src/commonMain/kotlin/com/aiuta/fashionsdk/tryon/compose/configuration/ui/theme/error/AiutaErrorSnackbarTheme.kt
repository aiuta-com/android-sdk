package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.colors.AiutaErrorSnackbarThemeColors
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.icons.AiutaErrorSnackbarThemeIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.strings.AiutaErrorSnackbarThemeStrings

@Immutable
public class AiutaErrorSnackbarTheme private constructor(
    public val strings: AiutaErrorSnackbarThemeStrings,
    public val icons: AiutaErrorSnackbarThemeIcons,
    public val colors: AiutaErrorSnackbarThemeColors,
) {
    public class Builder {
        public var strings: AiutaErrorSnackbarThemeStrings? = null
        public var icons: AiutaErrorSnackbarThemeIcons? = null
        public var colors: AiutaErrorSnackbarThemeColors? = null

        public fun build(): AiutaErrorSnackbarTheme {
            val parentClass = "AiutaErrorSnackbarTheme"

            return AiutaErrorSnackbarTheme(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                colors = colors.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "colors",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.errorSnackbar(
    block: AiutaErrorSnackbarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    errorSnackbar = AiutaErrorSnackbarTheme.Builder().apply(block).build()
}
