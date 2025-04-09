package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.selection.colors

import androidx.compose.ui.graphics.Color

public interface AiutaSelectionSnackbarThemeColors {
    public val selectionBackground: Color

    public class Default : AiutaSelectionSnackbarThemeColors {
        override val selectionBackground: Color = Color(0xFF000000)
    }
}
