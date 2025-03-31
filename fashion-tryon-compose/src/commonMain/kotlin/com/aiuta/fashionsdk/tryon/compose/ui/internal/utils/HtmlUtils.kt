package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

@Composable
internal expect fun buildAnnotatedStringFromHtml(
    input: String,
    isClickable: Boolean = true,
): AnnotatedString
