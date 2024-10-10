package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.CustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.TurkishLanguage

@Composable
internal fun resolveInternalLanguage(
    selectedLanguage: AiutaTryOnLanguage,
): InternalAiutaTryOnLanguage {
    return remember(selectedLanguage) {
        solveInternalLanguage(language = selectedLanguage)
    }
}

private fun solveInternalLanguage(language: AiutaTryOnLanguage): InternalAiutaTryOnLanguage {
    return when (language) {
        is EnglishLanguage -> EnglishAiutaTryOnLanguage
        is TurkishLanguage -> TurkishAiutaTryOnLanguage
        is RussianLanguage -> RussianAiutaTryOnLanguage
        is CustomLanguage -> language
    }
}
