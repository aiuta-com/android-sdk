package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@Composable
internal fun AiutaLabel(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val buttonText =
        buildAnnotatedString {
            append(stringResources.imageSelectorPoweredByAiuta1)
            withStyle(style = SpanStyle(color = theme.colors.aiuta)) {
                append(stringResources.imageSelectorPoweredByAiuta2)
            }
        }

    Box(
        modifier =
            modifier
                .background(
                    shape = RoundedCornerShape(100.dp),
                    color = theme.colors.gray1,
                )
                .clickableUnindicated { onClick() }
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            color = theme.colors.primary,
        )
    }
}
