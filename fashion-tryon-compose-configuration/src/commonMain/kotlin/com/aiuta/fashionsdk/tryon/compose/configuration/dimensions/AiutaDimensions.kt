package com.aiuta.fashionsdk.tryon.compose.configuration.dimensions

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

/**
 * Represents the dimensions used in the Aiuta fashion try-on application.
 *
 * @property grabberPaddingTop The top padding for the grabber element.
 * @property grabberWidth The width of the grabber element.
 */
@Immutable
public class AiutaDimensions(
    // Grabber
    public val grabberPaddingTop: Dp? = null,
    public val grabberWidth: Dp? = null,
)
