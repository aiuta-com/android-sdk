package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // App bar
    val appBarHistory: String
    val appBarSelect: String

    // Model selector
    val modelSelectorErrorEmptyModelsList: String

    // History
    val historySelectorEnableButtonSelectAll: String
    val historySelectorEnableButtonUnselectAll: String
    val historyEmptyDescription: String

    // Bottom sheets

    // General
    val cancel: String
    val tryAgain: String
    val defaultErrorMessage: String
}
