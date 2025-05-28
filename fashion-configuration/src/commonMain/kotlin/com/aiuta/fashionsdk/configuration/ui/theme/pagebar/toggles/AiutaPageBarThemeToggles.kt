package com.aiuta.fashionsdk.configuration.ui.theme.pagebar.toggles

/**
 * Toggle configuration for page bar behavior.
 * 
 * This interface defines behavior toggles that control the layout and appearance
 * of page navigation bars in the SDK. These settings affect the positioning and
 * arrangement of navigation elements.
 * 
 * 
 * @property preferCloseButtonOnTheRight Whether to position the close button on the right side of the page bar
 */
public interface AiutaPageBarThemeToggles {
    public val preferCloseButtonOnTheRight: Boolean

    /**
     * Default implementation of [AiutaPageBarThemeToggles].
     * 
     * This class provides standard behavior settings for page bars.
     * 
     * ```kotlin
     * theme {
     *     pageBar {
     *         toggles = AiutaPageBarThemeToggles.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaPageBarThemeToggles {
        override val preferCloseButtonOnTheRight: Boolean = false
    }
}
