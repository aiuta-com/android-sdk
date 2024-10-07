package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated

@Composable
internal fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit = {},
) {
    val theme = LocalTheme.current

    Box(
        modifier =
            modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(26.dp),
                )
                .clickableUnindicated { onClick() }
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = theme.typography.smallButton,
            color = textColor,
        )
    }
}
