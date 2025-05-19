package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors.AiutaPowerBarColorScheme
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun AiutaLabel(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    val powerBarTheme = theme.powerBar
    val aiutaColor = Color(0xFF4000FF)

    val highlightedText = remember {
        buildHighlightedString(
            originalString = powerBarTheme.strings.poweredByAiuta,
            keyword = "Aiuta",
            color = when (powerBarTheme.colors.aiuta) {
                AiutaPowerBarColorScheme.DEFAULT -> aiutaColor
                AiutaPowerBarColorScheme.PRIMARY -> theme.color.primary
            },
        )
    }

    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(100.dp),
                color = theme.color.neutral,
            )
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = highlightedText,
            style = theme.button.typography.buttonS,
            color = theme.color.primary,
        )
    }
}

private fun buildHighlightedString(
    originalString: String,
    keyword: String,
    color: Color,
): AnnotatedString {
    val keywordLength = keyword.length

    return buildAnnotatedString {
        var startIndex = 0
        while (startIndex < originalString.length) {
            val index = originalString.indexOf(keyword, startIndex)
            if (index == -1) {
                append(originalString.substring(startIndex))
                break
            }

            // Add text before keyword
            append(originalString.substring(startIndex, index))

            // Add keyword with color
            withStyle(
                style = SpanStyle(color = color),
            ) {
                append(keyword)
            }

            startIndex = index + keywordLength
        }
    }
}
