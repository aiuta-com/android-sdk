package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.configuration.language.AiutaTryOnLanguage

public class CustomLanguage(
    // History
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    // General
    override val cancel: String,
    override val tryAgain: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage,
    InternalAiutaTryOnLanguage
