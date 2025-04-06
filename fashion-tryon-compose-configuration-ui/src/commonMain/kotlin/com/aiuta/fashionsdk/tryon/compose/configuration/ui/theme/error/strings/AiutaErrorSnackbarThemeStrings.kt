package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.error.strings

public interface AiutaErrorSnackbarThemeStrings {
    public val defaultErrorMessage: String
    public val tryAgainButton: String

    public class Default : AiutaErrorSnackbarThemeStrings {
        override val defaultErrorMessage: String = "Something went wrong. Please try again later"
        override val tryAgainButton: String = "Try again"
    }
}
