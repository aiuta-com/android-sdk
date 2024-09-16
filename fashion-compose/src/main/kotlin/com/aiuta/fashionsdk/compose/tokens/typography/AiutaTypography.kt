package com.aiuta.fashionsdk.compose.tokens.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
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
                titleL =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = (28.13).sp,
                    ).withAiutaFont(titleL),
                titleM =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        lineHeight = (23.44).sp,
                        letterSpacing = (-0.02).em,
                    ).withAiutaFont(titleM),
                navbar =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.03).em,
                    ).withAiutaFont(navbar),
                regular =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.03).em,
                    ).withAiutaFont(regular),
                button =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(button),
                smallButton =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(smallButton),
                cells =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        lineHeight = (19.92).sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(cells),
                chips =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(chips),
                productName =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        lineHeight = (15.23).sp,
                    ).withAiutaFont(productName),
                price =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = (16.41).sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(price),
                brandName =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = (14.06).sp,
                        letterSpacing = (-0.01).em,
                    ).withAiutaFont(brandName),
                description =
                    TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = (14.06).sp,
                        letterSpacing = (-0.01).em,
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
