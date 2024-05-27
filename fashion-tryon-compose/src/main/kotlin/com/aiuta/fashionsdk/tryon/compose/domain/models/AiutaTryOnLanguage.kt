package com.aiuta.fashionsdk.tryon.compose.domain.models

// TODO Docs
public enum class AiutaTryOnLanguage(
    // Code in ISO-639
    internal val code: String,
) {
    ENGLISH(code = "en"),

    TURKISH(code = "tr"),

    RUSSIAN(code = "ru"),
}

// TODO Docs
internal val defaultAiutaTryOnLanguage: AiutaTryOnLanguage = AiutaTryOnLanguage.ENGLISH
