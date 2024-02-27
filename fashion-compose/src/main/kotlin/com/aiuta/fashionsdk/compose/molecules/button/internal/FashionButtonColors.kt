package com.aiuta.fashionsdk.compose.molecules.button.internal

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.FashionColor

internal object FashionButtonColors {
    @Composable
    fun primaryColors() =
        DefaultFashionButtonColor(
            buttonColors =
                ButtonDefaults.buttonColors(
                    backgroundColor = FashionColor.ElectricBlue,
                    contentColor = FashionColor.White,
                    disabledBackgroundColor = FashionColor.Gray,
                    disabledContentColor = FashionColor.White,
                ),
            rippleColor = FashionColor.Black.copy(alpha = 0.1f),
        )

    @Composable
    fun errorColors() =
        DefaultFashionButtonColor(
            buttonColors =
                ButtonDefaults.buttonColors(
                    backgroundColor = FashionColor.RedError,
                    contentColor = FashionColor.White,
                    disabledBackgroundColor = FashionColor.Gray,
                    disabledContentColor = FashionColor.White,
                ),
            rippleColor = FashionColor.Black.copy(alpha = 0.1f),
        )

    @Composable
    fun secondaryColors() =
        DefaultFashionButtonColor(
            buttonColors =
                ButtonDefaults.buttonColors(
                    backgroundColor = FashionColor.LightGray,
                    contentColor = FashionColor.ElectricBlue,
                    disabledBackgroundColor = FashionColor.LightGray,
                    disabledContentColor = FashionColor.DarkGray,
                ),
            rippleColor = FashionColor.Black.copy(alpha = 0.1f),
        )

    @Composable
    fun transparentColors(
        backgroundColor: Color = Color.Transparent,
        contentColor: Color = FashionColor.ElectricBlue,
        disabledBackgroundColor: Color = Color.Transparent,
        disabledContentColor: Color = FashionColor.DarkGray,
        rippleColor: Color = FashionColor.Black.copy(alpha = 0.1f),
    ) = DefaultFashionButtonColor(
        buttonColors =
            ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
                disabledBackgroundColor = disabledBackgroundColor,
                disabledContentColor = disabledContentColor,
            ),
        rippleColor = rippleColor,
    )

    @Composable
    fun inverseColors() =
        DefaultFashionButtonColor(
            buttonColors =
                ButtonDefaults.buttonColors(
                    backgroundColor = FashionColor.White,
                    contentColor = FashionColor.ElectricBlue,
                    disabledBackgroundColor = FashionColor.White,
                    disabledContentColor = FashionColor.DarkGray,
                ),
            rippleColor = FashionColor.ElectricBlue.copy(alpha = 0.15f),
        )

    @Composable
    fun outlineColors() =
        OutlineFashionButtonColor(
            buttonColors =
                ButtonDefaults.outlinedButtonColors(
                    backgroundColor = FashionColor.White,
                    contentColor = FashionColor.Black,
                    disabledContentColor = FashionColor.DarkGray,
                ),
            borderColor = Color(0xFFCCCCCC),
            rippleColor = Color(0xFFCCCCCC).copy(alpha = 0.15f),
        )
}
