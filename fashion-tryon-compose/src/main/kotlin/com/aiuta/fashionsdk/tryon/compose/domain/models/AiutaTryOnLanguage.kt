package com.aiuta.fashionsdk.tryon.compose.domain.models

/**
 * One of the languages supported by AiutaTryOn flow
 */
public enum class AiutaTryOnLanguage(
    // Code in ISO-639
    internal val code: String,
) {
    ENGLISH(code = "en"),

    TURKISH(code = "tr"),

    RUSSIAN(code = "ru"),
}

/**
 * Default language for AiutaTryOn flow
 */
internal val defaultAiutaTryOnLanguage: AiutaTryOnLanguage = AiutaTryOnLanguage.ENGLISH
