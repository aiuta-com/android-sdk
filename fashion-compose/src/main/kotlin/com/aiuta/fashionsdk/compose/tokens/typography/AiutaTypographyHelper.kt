package com.aiuta.fashionsdk.compose.tokens.typography

import androidx.compose.ui.text.TextStyle

internal fun TextStyle.withAiutaFont(aiutaFont: AiutaFont?): TextStyle {
    val finalFontFamily = aiutaFont?.fontFamily ?: fontFamily
    val finalFontWeight = aiutaFont?.fontWeight ?: fontWeight

    return copy(
        fontFamily = finalFontFamily,
        fontWeight = finalFontWeight,
    )
}
