package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnLanguage
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

@Composable
private fun solveLocale(): AiutaTryOnLanguage? {
    val systemLocaleCode = Locale.current.language
    return when (systemLocaleCode) {
        AiutaTryOnLanguage.ENGLISH.code -> AiutaTryOnLanguage.ENGLISH
        AiutaTryOnLanguage.TURKISH.code -> AiutaTryOnLanguage.TURKISH
        AiutaTryOnLanguage.RUSSIAN.code -> AiutaTryOnLanguage.RUSSIAN
        else -> null
    }
}

private fun solveInternalLanguage(language: AiutaTryOnLanguage): InternalAiutaTryOnLanguage {
    return when (language) {
        AiutaTryOnLanguage.ENGLISH -> EnglishAiutaTryOnLanguage
        AiutaTryOnLanguage.TURKISH -> TurkishAiutaTryOnLanguage
        AiutaTryOnLanguage.RUSSIAN -> RussianAiutaTryOnLanguage
    }
}
