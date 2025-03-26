package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.EnglishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.RussianAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations.TurkishAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.CustomLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.RussianLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.TurkishLanguage

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
        is EnglishLanguage ->
            EnglishAiutaTryOnLanguage(
                brand = language.brand,
                termsOfServiceUrl = language.termsOfServiceUrl,
                privacyPolicyUrl = language.privacyPolicyUrl,
                onboardingPageConsentSupplementaryPoints = language.onboardingPageConsentSupplementaryPoints,
            )

        is TurkishLanguage ->
            TurkishAiutaTryOnLanguage(
                brand = language.brand,
                termsOfServiceUrl = language.termsOfServiceUrl,
                privacyPolicyUrl = language.privacyPolicyUrl,
                onboardingPageConsentSupplementaryPoints = language.onboardingPageConsentSupplementaryPoints,
            )

        is RussianLanguage ->
            RussianAiutaTryOnLanguage(
                brand = language.brand,
                termsOfServiceUrl = language.termsOfServiceUrl,
                privacyPolicyUrl = language.privacyPolicyUrl,
                onboardingPageConsentSupplementaryPoints = language.onboardingPageConsentSupplementaryPoints,
            )

        is CustomLanguage -> language
    }
}
