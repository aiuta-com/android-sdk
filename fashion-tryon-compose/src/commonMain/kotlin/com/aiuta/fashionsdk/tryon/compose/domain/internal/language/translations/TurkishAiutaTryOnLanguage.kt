package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class TurkishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // History
    override val historySelectorEnableButtonSelectAll: String = "Tümünü seçin"
    override val historySelectorEnableButtonUnselectAll: String = "Tümünü Kaldır"

    // Invalid image

    // General
    override val cancel: String = "Vazgeç"
    override val tryAgain: String = "Tekrar dene"
    override val defaultErrorMessage: String = "Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin"
}
