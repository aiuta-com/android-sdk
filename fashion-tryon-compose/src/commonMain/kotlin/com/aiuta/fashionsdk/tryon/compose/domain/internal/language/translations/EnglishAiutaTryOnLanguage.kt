package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class EnglishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {

    // History
    override val historySelectorEnableButtonSelectAll: String = "Select all"
    override val historySelectorEnableButtonUnselectAll: String = "Unselect all"

    // General
    override val cancel: String = "Cancel"
    override val tryAgain: String = "Try again"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}
