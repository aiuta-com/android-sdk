@file:Suppress("LongParameterList")

package com.aiuta.fashionsdk.compose.molecules.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonLoadingAnimation
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonSize
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionRippleTheme

@Composable
public fun FashionButton(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    elevation: ButtonElevation =
        elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    @DrawableRes iconRes: Int? = null,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    text: String? = null,
    style: FashionButtonStyle,
    size: FashionButtonSize,
    onClick: () -> Unit,
) {
    val transition = updateTransition(isLoading, label = "loading button state")

    CompositionLocalProvider(
        LocalRippleTheme provides FashionRippleTheme(style.colors.rippleColor),
    ) {
        Button(
            modifier = modifier,
            shape = size.shape,
            border = style.border,
            colors = style.colors.buttonColors,
            contentPadding = size.paddingValues,
            elevation = elevation,
            enabled = isEnable,
            onClick = {
                if (!isLoading) {
                    onClick()
                }
            },
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                transition.AnimatedContent { loading ->
                    if (loading) {
                        FashionButtonLoadingAnimation()
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            iconRes?.let {
                                Icon(
                                    modifier = Modifier.size(size.iconSize),
                                    tint =
                                        style.colors.buttonColors.contentColor(
                                            enabled = isEnable,
                                        ).value,
                                    imageVector = ImageVector.vectorResource(id = iconRes),
                                    contentDescription = contentDescription,
                                )
                            }
                            text?.let {
                                Text(
                                    style = size.textStyle,
                                    textAlign = TextAlign.Center,
                                    color =
                                        style.colors.buttonColors.contentColor(
                                            enabled = isEnable,
                                        ).value,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier =
                                        Modifier.padding(
                                            start =
                                                if (iconRes != null) {
                                                    size.iconSpacing
                                                } else {
                                                    0.dp
                                                },
                                        ),
                                    text = text,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
