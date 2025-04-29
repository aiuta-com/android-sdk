package com.aiuta.fashionsdk.configuration.ui.theme.selection

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.selection.colors.AiutaSelectionSnackbarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.selection.icons.AiutaSelectionSnackbarThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.selection.strings.AiutaSelectionSnackbarThemeStrings

@Immutable
public class AiutaSelectionSnackbarTheme(
    public val strings: AiutaSelectionSnackbarThemeStrings,
    public val icons: AiutaSelectionSnackbarThemeIcons,
    public val colors: AiutaSelectionSnackbarThemeColors,
) {
    public class Builder {
        public var strings: AiutaSelectionSnackbarThemeStrings? = null
        public var icons: AiutaSelectionSnackbarThemeIcons? = null
        public var colors: AiutaSelectionSnackbarThemeColors? = null

        public fun build(): AiutaSelectionSnackbarTheme {
            val parentClass = "AiutaSelectionSnackbarTheme"

            return AiutaSelectionSnackbarTheme(
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

public inline fun AiutaTheme.Builder.selectionSnackbar(
    block: AiutaSelectionSnackbarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    selectionSnackbar = AiutaSelectionSnackbarTheme.Builder().apply(block).build()
}
