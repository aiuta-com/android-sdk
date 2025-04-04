package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // History
    val historySelectorEnableButtonSelectAll: String
    val historySelectorEnableButtonUnselectAll: String

    // Bottom sheets

    // General
    val cancel: String
    val tryAgain: String
    val defaultErrorMessage: String
}
