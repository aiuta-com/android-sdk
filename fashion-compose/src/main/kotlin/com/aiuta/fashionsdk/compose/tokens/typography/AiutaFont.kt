package com.aiuta.fashionsdk.compose.tokens.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/**
 * Class representing a font configuration in the Aiuta SDK.
 *
 * This class allows the host application to specify custom font family and weight
 * for text elements used in the SDK.
 *
 * @property fontFamily The font family to be used. If null, the default font family is used.
 * @property fontWeight The font weight to be used. If null, the default font weight is used.
 */
public class AiutaFont(
    public val fontFamily: FontFamily? = null,
    public val fontWeight: FontWeight? = null,
)
