package com.aiuta.fashionsdk.configuration.ui.theme.sheet.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shape configuration for bottom sheet components.
 *
 * This abstract class defines the corner radius values used for shaping various
 * bottom sheet elements, including the main sheet container and chip buttons.
 * It provides a consistent visual appearance for rounded corners throughout
 * the bottom sheet interface.
 *
 *
 * @property bottomSheet Corner radius for the main bottom sheet container
 * @property chipsButton Corner radius for chip-style buttons within the sheet
 * @property bottomSheetShape Pre-computed shape for the bottom sheet container
 * @property chipsButtonShape Pre-computed shape for chip buttons
 */
public abstract class AiutaBottomSheetThemeShapes {
    public abstract val bottomSheet: Dp
    public abstract val chipsButton: Dp

    public val bottomSheetShape: CornerBasedShape by lazy { RoundedCornerShape(topStart = bottomSheet, topEnd = bottomSheet) }
    public val chipsButtonShape: CornerBasedShape by lazy { RoundedCornerShape(chipsButton) }

    /**
     * Default implementation of [AiutaBottomSheetThemeShapes].
     *
     * This class provides standard corner radius values for bottom sheet elements.
     *
     * ```kotlin
     * theme {
     *     bottomSheet {
     *         shapes = AiutaBottomSheetThemeShapes.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaBottomSheetThemeShapes() {
        override val bottomSheet: Dp = 16.dp
        override val chipsButton: Dp = 8.dp
    }
}
