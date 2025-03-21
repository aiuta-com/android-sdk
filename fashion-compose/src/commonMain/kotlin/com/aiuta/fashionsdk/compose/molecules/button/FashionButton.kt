@file:Suppress("LongParameterList")

package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonLoadingAnimation
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonSize
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.GradientButtonStyle
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.utils.conditional

@Composable
public fun FashionButton(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    elevation: ButtonElevation =
        elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    icon: AiutaIcons.AiutaIcon? = null,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    text: String? = null,
    style: FashionButtonStyle,
    size: FashionButtonSize,
    onClick: () -> Unit,
) {
    val transition = updateTransition(isLoading, label = "loading button state")

    Button(
        modifier =
            modifier
                .conditional(style is GradientButtonStyle) {
                    (style as? GradientButtonStyle)?.gradientBackground?.let {
                        background(
                            brush = it,
                            shape = size.shape,
                        )
                    } ?: this
                },
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
                        icon?.let {
                            AiutaIcon(
                                modifier = Modifier.size(size.iconSize),
                                tint =
                                    style.colors.buttonColors.contentColor(
                                        enabled = isEnable,
                                    ).value,
                                icon = icon,
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
                                            if (icon != null) {
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
