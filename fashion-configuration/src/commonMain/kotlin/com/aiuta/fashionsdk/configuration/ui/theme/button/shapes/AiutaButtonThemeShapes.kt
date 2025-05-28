package com.aiuta.fashionsdk.configuration.ui.theme.button.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shape configuration for button components.
 *
 * This abstract class defines the corner radius values used for shaping different
 * sizes of buttons in the SDK. It provides a consistent visual appearance for
 * rounded corners across various button types.
 *
 *
 * @property buttonM Corner radius for medium-sized buttons
 * @property buttonS Corner radius for small-sized buttons
 * @property buttonMShape Pre-computed shape for medium-sized buttons
 * @property buttonSShape Pre-computed shape for small-sized buttons
 */
public abstract class AiutaButtonThemeShapes {
    public abstract val buttonM: Dp
    public abstract val buttonS: Dp

    public val buttonMShape: CornerBasedShape by lazy { RoundedCornerShape(buttonM) }
    public val buttonSShape: CornerBasedShape by lazy { RoundedCornerShape(buttonS) }

    /**
     * Default implementation of [AiutaButtonThemeShapes].
     *
     * This class provides standard corner radius values for buttons:
     * - Medium buttons: 8dp
     * - Small buttons: 8dp
     *
     * ```kotlin
     * theme {
     *     button {
     *         shapes = AiutaButtonThemeShapes.Default()
     *     }
     * }
     * ```
     */
    public class Default : AiutaButtonThemeShapes() {
        override val buttonM: Dp = 8.dp
        override val buttonS: Dp = 8.dp
    }
}
