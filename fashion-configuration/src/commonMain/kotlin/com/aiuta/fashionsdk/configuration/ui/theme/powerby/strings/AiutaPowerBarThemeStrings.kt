package com.aiuta.fashionsdk.configuration.ui.theme.powerby.strings

/**
 * Text content configuration for the "Powered by Aiuta" branding bar.
 * 
 * This interface defines the text strings used in the power bar throughout
 * the SDK. It provides consistent branding messaging that can be customized
 * to match your app's language and tone.
 * 
 * 
 * @property poweredByAiuta Text displayed in the power bar
 */
public interface AiutaPowerBarThemeStrings {
    public val poweredByAiuta: String

    /**
     * Default implementation of [AiutaPowerBarThemeStrings].
     * 
     * This class provides standard English text strings for the power bar.
     * 
     * ```kotlin
     * theme {
     *     powerBar {
     *         strings = AiutaPowerBarThemeStrings.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaPowerBarThemeStrings {
        override val poweredByAiuta: String = "Powered by Aiuta"
    }
}
