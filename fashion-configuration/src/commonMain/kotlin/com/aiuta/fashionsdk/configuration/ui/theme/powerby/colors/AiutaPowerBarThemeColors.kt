package com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors

/**
 * Color configuration for the "Powered by Aiuta" branding bar.
 *
 * This interface defines the color scheme used in the power bar throughout
 * the SDK. It provides consistent branding styling that can be customized
 * to match your app's design system.
 *
 *
 * @property aiuta Color scheme for the power bar branding elements
 */
public interface AiutaPowerBarThemeColors {
    public val aiuta: AiutaPowerBarColorScheme

    /**
     * Default implementation of [AiutaPowerBarThemeColors].
     *
     * This class provides standard colors for the power bar:
     * - Aiuta branding: Standard color scheme
     *
     * ```kotlin
     * theme {
     *     powerBar {
     *         colors = AiutaPowerBarThemeColors.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaPowerBarThemeColors {
        override val aiuta: AiutaPowerBarColorScheme = AiutaPowerBarColorScheme.STANDARD
    }
}
