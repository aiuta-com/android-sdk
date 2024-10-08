package com.aiuta.fashionsdk.compose.tokens.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Class representing the typography styles used in the Aiuta SDK.
 *
 * This class provides various text styles for different UI components, allowing
 * customization of font family, weight.
 *
 * @see AiutaTheme
 */
@Immutable
public class AiutaTypography private constructor(
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
) {
    public companion object {
        public fun build(
            titleXL: AiutaFont? = null,
            welcomeText: AiutaFont? = null,
            titleL: AiutaFont? = null,
            titleM: AiutaFont? = null,
            navbar: AiutaFont? = null,
            regular: AiutaFont? = null,
            button: AiutaFont? = null,
            smallButton: AiutaFont? = null,
            cells: AiutaFont? = null,
            chips: AiutaFont? = null,
            productName: AiutaFont? = null,
            price: AiutaFont? = null,
            brandName: AiutaFont? = null,
            description: AiutaFont? = null,
        ): AiutaTypography {
            return AiutaTypography(
                titleXL =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Black,
                        fontSize = 40.sp,
                        lineHeight = 44.sp,
                    ).withAiutaFont(titleXL),
                welcomeText =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ).withAiutaFont(welcomeText),
                titleL =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                    ).withAiutaFont(titleL),
                titleM =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    ).withAiutaFont(titleM),
                navbar =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(navbar),
                regular =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                    ).withAiutaFont(regular),
                button =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                    ).withAiutaFont(button),
                smallButton =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(smallButton),
                cells =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(cells),
                chips =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(chips),
                productName =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(productName),
                price =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(price),
                brandName =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.04.sp,
                    ).withAiutaFont(brandName),
                description =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    ).withAiutaFont(description),
            )
        }
    }
}

/**
 * Default instance of [AiutaTypography] with predefined text styles.
 *
 * @see AiutaTypography
 */
public val DefaultAiutaTypography: AiutaTypography by lazy { AiutaTypography.build() }
