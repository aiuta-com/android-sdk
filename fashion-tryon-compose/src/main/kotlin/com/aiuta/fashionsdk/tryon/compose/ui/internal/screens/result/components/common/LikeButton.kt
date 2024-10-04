package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.icon.wishlist24
import com.aiuta.fashionsdk.compose.tokens.icon.wishlistFill24
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated

@Composable
internal fun LikeButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    iconSize: Dp,
    onClick: (currentState: Boolean) -> Unit,
) {
    val theme = LocalTheme.current

    Box(
        modifier =
            modifier
                .background(
                    color = theme.colors.background,
                    shape = CircleShape,
                )
                .clickableUnindicated {
                    onClick(isLiked)
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter =
                rememberAsyncImagePainter(
                    model =
                        if (isLiked) {
                            theme.icons.wishlistFill24
                        } else {
                            theme.icons.wishlist24
                        },
                ),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}
