package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

@Composable
internal actual fun buildAnnotatedStringFromHtml(
    input: String,
    isClickable: Boolean,
): AnnotatedString = buildAnnotatedString {
    // TODO Support Native platform
    append(input)
}
