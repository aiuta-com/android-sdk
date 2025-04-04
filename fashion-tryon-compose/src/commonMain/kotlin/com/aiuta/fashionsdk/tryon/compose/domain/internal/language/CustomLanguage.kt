package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.configuration.language.AiutaTryOnLanguage

public class CustomLanguage(
    // App bar
    override val appBarHistory: String,
    override val appBarSelect: String,
    // Image selector
    override val modelSelectorErrorEmptyModelsList: String,
    // History
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    override val historyEmptyDescription: String,
    // General
    override val cancel: String,
    override val tryAgain: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage,
    InternalAiutaTryOnLanguage
