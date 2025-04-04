package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class RussianAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // History
    override val historySelectorEnableButtonSelectAll: String = "Выбрать все"
    override val historySelectorEnableButtonUnselectAll: String = "Снять выделение"

    // General
    override val cancel: String = "Отмена"
    override val tryAgain: String = "Попробовать снова"
    override val defaultErrorMessage: String =
        "Что-то пошло не так. Пожалуйста, повторите попытку позже"
}
