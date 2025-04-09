package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.selection.strings

public interface AiutaSelectionSnackbarThemeStrings {
    public val select: String
    public val cancel: String
    public val selectAll: String
    public val unselectAll: String

    public class Default : AiutaSelectionSnackbarThemeStrings {
        override val select: String = "Select"
        override val cancel: String = "Cancel"
        override val selectAll: String = "Select All"
        override val unselectAll: String = "Unselect all"
    }
}
