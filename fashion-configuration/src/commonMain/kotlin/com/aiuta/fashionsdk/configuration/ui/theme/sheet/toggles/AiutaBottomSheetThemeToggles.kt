package com.aiuta.fashionsdk.configuration.ui.theme.sheet.toggles

/**
 * Toggle configuration for bottom sheet behavior.
 *
 * This interface defines behavior toggles that control how bottom sheet delimiters
 * are displayed and extended. These settings affect the visual appearance and
 * layout of delimiters within the bottom sheet.
 *
 *
 * @property extendDelimitersToTheRight Whether to extend delimiters to the right edge
 * @property extendDelimitersToTheLeft Whether to extend delimiters to the left edge
 */
public interface AiutaBottomSheetThemeToggles {
    public val extendDelimitersToTheRight: Boolean
    public val extendDelimitersToTheLeft: Boolean

    /**
     * Default implementation of [AiutaBottomSheetThemeToggles].
     *
     * This class provides standard behavior settings for bottom sheet delimiters
     *
     * ```kotlin
     * theme {
     *     bottomSheet {
     *         toggles = AiutaBottomSheetThemeToggles.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaBottomSheetThemeToggles {
        override val extendDelimitersToTheRight: Boolean = false
        override val extendDelimitersToTheLeft: Boolean = false
    }
}
