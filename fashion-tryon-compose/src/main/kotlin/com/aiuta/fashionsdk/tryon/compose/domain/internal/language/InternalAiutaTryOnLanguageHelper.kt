package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.CustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.TurkishLanguage

internal fun InternalAiutaTryOnLanguage.isoCode(): String? {
    return when (this) {
        is EnglishAiutaTryOnLanguage -> EnglishLanguage.code
        is RussianAiutaTryOnLanguage -> RussianLanguage.code
        is TurkishAiutaTryOnLanguage -> TurkishLanguage.code
        else -> (this as? CustomLanguage)?.code
    }
}
