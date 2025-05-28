package com.aiuta.fashionsdk.configuration.ui.theme.error.strings

/**
 * Text content configuration for error snackbar notifications.
 *
 * This interface defines the text strings used in error snackbar notifications
 * throughout the SDK. It provides consistent error messaging that can be
 * customized to match your app's language and tone.
 *
 *
 * @property defaultErrorMessage Default error message shown when an error occurs
 * @property tryAgainButton Text for the retry action button
 */
public interface AiutaErrorSnackbarThemeStrings {
    public val defaultErrorMessage: String
    public val tryAgainButton: String

    /**
     * Default implementation of [AiutaErrorSnackbarThemeStrings].
     *
     * This class provides standard English text strings for error snackbars.
     *
     * ```kotlin
     * theme {
     *     errorSnackbar {
     *         strings = AiutaErrorSnackbarThemeStrings.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaErrorSnackbarThemeStrings {
        override val defaultErrorMessage: String = "Something went wrong. Please try again later"
        override val tryAgainButton: String = "Try again"
    }
}
