package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.configuration.language.TranslationWord
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class RussianAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "История"
    override val appBarSelect: String = "Выбрать"

    // Image selector
    override val imageSelectorChangeButton: String = "Заменить фото"
    override val imageSelectorPoweredByAiuta: String = "Powered by Aiuta"
    override val imageSelectorProtectionPoint: String = "Ваши фото защищены и видны только вам"

    // Model selector
    override val modelSelectorCategories: List<TranslationWord> =
        listOf(
            TranslationWord(id = "woman", translation = "Женщины"),
            TranslationWord(id = "man", translation = "Мужчины"),
        )
    override val modelSelectorErrorEmptyModelsList: String = "Список моделей пуст"

    // History
    override val historySelectorEnableButtonSelectAll: String = "Выбрать все"
    override val historySelectorEnableButtonUnselectAll: String = "Снять выделение"
    override val historyEmptyDescription: String = "История ваших примерок будет сохранена здесь"

    // Generation Result
    override val generationResultMoreTitle: String = "Вам может понравиться"
    override val generationResultMoreSubtitle: String = "Больше образов для примерки"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Сделать фото"
    override val pickerSheetChooseLibrary: String = "Выбрать из библиотеки"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Ранее использованные фото"
    override val generatedOperationsSheetUploadNewButton: String = "+ Загрузить новое фото"
    override val generatedOperationsSheetUploadNewButtonWithModels: String = "+ Загрузить новое фото или модель"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Пропустить"
    override val feedbackSheetSend: String = "Отправить"
    override val feedbackSheetSendFeedback: String = "Отправить отзыв"

    // Disclaimer
    override val fitDisclaimerTitle: String = "Результат может отличаться от реальной посадки"
    override val fitDisclaimerBody: String = "Виртуальная примерка - это инструмент визуализации, который помогает представить, как вещь будет выглядеть на вас. Реальная посадка может отличаться."

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Разрешение использования камеры"
    override val dialogCameraPermissionDescription: String =
        "Пожалуйста, предоставьте доступ к камере в настройках приложения."
    override val dialogCameraPermissionConfirmButton: String = "Настройки"

    // Invalid image
    override val dialogInvalidImageDescription: String = "Мы не смогли обнаружить никого на этом фото"

    // General
    override val addToWish: String = "В избранное"
    override val addToCart: String = "Добавить в корзину"
    override val cancel: String = "Отмена"
    override val close: String = "Закрыть"
    override val modelSelect: String = "Выберите вашу модель"
    override val tryOn: String = "Примерить"
    override val tryAgain: String = "Попробовать снова"
    override val virtualTryOn: String = "Виртуальная примерка"
    override val share: String = "Поделиться"
    override val defaultErrorMessage: String =
        "Что-то пошло не так. Пожалуйста, повторите попытку позже"
}
