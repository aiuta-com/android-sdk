package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnLanguage

internal fun InternalAiutaTryOnLanguage.isoCode(): String {
    return when (this) {
        is EnglishAiutaTryOnLanguage -> AiutaTryOnLanguage.ENGLISH.code
        is RussianAiutaTryOnLanguage -> AiutaTryOnLanguage.RUSSIAN.code
        is TurkishAiutaTryOnLanguage -> AiutaTryOnLanguage.TURKISH.code
        else -> throw IllegalStateException("Not supported language")
    }
}
