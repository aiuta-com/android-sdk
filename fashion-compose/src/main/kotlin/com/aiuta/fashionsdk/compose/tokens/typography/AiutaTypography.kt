package com.aiuta.fashionsdk.compose.tokens.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

/**
 * Class representing the typography styles used in the Aiuta SDK.
 *
 * This class provides various text styles for different UI components, allowing
 * customization of font family, weight.
 *
 * @see AiutaTheme
 */
@Immutable
public class AiutaTypography(
    public val titleXL: TextStyle,
    public val welcomeText: TextStyle,
    public val titleL: TextStyle,
    public val titleM: TextStyle,
    public val navbar: TextStyle,
    public val regular: TextStyle,
    public val button: TextStyle,
    public val smallButton: TextStyle,
    public val cells: TextStyle,
    public val chips: TextStyle,
    public val productName: TextStyle,
    public val price: TextStyle,
    public val brandName: TextStyle,
    public val description: TextStyle,
)

/**
 * Default instance of [AiutaTypography] with predefined text styles.
 *
 * @see AiutaTypography
 */
public val DefaultAiutaTypography: AiutaTypography by lazy { aiutaTypography() }
