package com.aiuta.fashionsdk.configuration.ui.theme.sheet.grabber

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Theme configuration for the bottom sheet grabber (drag handle).
 *
 * This interface defines the visual styling for the grabber element that appears
 * at the top of bottom sheets, allowing users to drag and interact with the sheet.
 * It provides customization options for the grabber's dimensions and positioning.
 *
 *
 * @property width The width of the grabber element
 * @property height The height/thickness of the grabber element
 * @property topPadding The padding between the top of the sheet and the grabber
 */
public interface AiutaBottomSheetThemeGrabber {
    public val width: Dp
    public val height: Dp
    public val topPadding: Dp

    /**
     * Default implementation of [AiutaBottomSheetThemeGrabber].
     *
     * This class provides standard dimensions for the bottom sheet grabber
     *
     * ```kotlin
     * theme {
     *     bottomSheet {
     *         grabber = AiutaBottomSheetThemeGrabber.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaBottomSheetThemeGrabber {
        override val width: Dp = 36.dp
        override val height: Dp = 3.dp
        override val topPadding: Dp = 8.dp
    }
}
