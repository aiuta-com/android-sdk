package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.CustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.TurkishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.defaultAiutaTryOnLanguage

@Composable
internal fun resolveInternalLanguage(
    selectedLanguage: AiutaTryOnLanguage?,
): InternalAiutaTryOnLanguage {
    val finalLanguage =
        selectedLanguage
            ?: solveLocale()
            ?: defaultAiutaTryOnLanguage

    return remember {
        solveInternalLanguage(
            language = finalLanguage,
        )
    }
}

private fun solveLocale(): AiutaTryOnLanguage? {
    val systemLocaleCode = Locale.current.language
    return when (systemLocaleCode) {
        EnglishLanguage.code -> EnglishLanguage
        TurkishLanguage.code -> TurkishLanguage
        RussianLanguage.code -> RussianLanguage
        else -> null
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
