package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.language.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.language.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.language.TurkishLanguage
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.MultiLanguagePhrase
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.isoCode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources

@Immutable
internal class MultiLanguagePhraseUiModel(
    val englishTranslation: String? = null,
    val russianTranslation: String? = null,
    val turkishTranslation: String? = null,
)

internal fun MultiLanguagePhrase.toUiModel(): MultiLanguagePhraseUiModel = MultiLanguagePhraseUiModel(
    englishTranslation = englishTranslation,
    russianTranslation = russianTranslation,
    turkishTranslation = turkishTranslation,
)

@Composable
internal fun MultiLanguagePhraseUiModel.toTranslatedString(): String? {
    val stringResources = LocalAiutaTryOnStringResources.current

    return toTranslatedString(stringResources)
}

internal fun MultiLanguagePhraseUiModel.toTranslatedString(
    stringResources: InternalAiutaTryOnLanguage,
): String? {
    val currentIsoCode = stringResources.isoCode()

    return when (currentIsoCode) {
        EnglishLanguage.CODE -> englishTranslation
        RussianLanguage.CODE -> russianTranslation
        TurkishLanguage.CODE -> turkishTranslation
        else -> null
    }
}
