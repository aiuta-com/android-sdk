package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.configuration.language.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.language.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.language.TurkishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage

internal fun InternalAiutaTryOnLanguage.isoCode(): String? {
    return when (this) {
        is EnglishAiutaTryOnLanguage -> EnglishLanguage.CODE
        is RussianAiutaTryOnLanguage -> RussianLanguage.CODE
        is TurkishAiutaTryOnLanguage -> TurkishLanguage.CODE
        else -> null
    }
}
