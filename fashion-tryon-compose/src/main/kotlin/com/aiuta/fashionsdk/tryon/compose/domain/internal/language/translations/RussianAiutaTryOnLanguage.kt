package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal object RussianAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "История"
    override val appBarSelect: String = "Выбрать"

    // Pre Onboarding
    override val preOnboardingTitle: String = "TODO"
    override val preOnboardingSubtitle: String = "TODO"
    override val preOnboardingButton: String = "TODO"

    // Onboarding
    override val onboardingButtonNext: String = "Далее"
    override val onboardingButtonStart: String = "Начать"
    override val onboardingPageTryonTopic: String = "Примерьте перед покупкой"
    override val onboardingPageTryonSubtopic: String = "Загрузите своё фото и порадуйтесь новому образу"
    override val onboardingPageBestResultTopic: String = "Для лучшего качества примерки"
    override val onboardingPageBestResultSubtopic: String = "Используйте фото с хорошим освещением и прямой позой"
    override val onboardingPageConsentTopic: String = "TODO"
    override val onboardingPageConsentBody: String = "TODO"
    override val onboardingPageConsentAgreePoint: String = "TODO"
    override val onboardingAppbarTryonPage: String = "TODO"
    override val onboardingAppbarBestResultPage: String = "TODO"
    override val onboardingAppbarConsentPage: String = "TODO"

    // Image selector
    override val imageSelectorUploadButton: String = "Загрузить своё фото"
    override val imageSelectorChangeButton: String = "Заменить фото"
    override val imageSelectorPoweredByAiuta: String = "Powered by Aiuta"
    override val imageSelectorProtectionPoint: String = "TODO"
    override val imageSelectorGeneratingOutfit: String = "Генерируем образ"

    // History
    override val historySelectorDisabledButton: String = "Выбрать"
    override val historySelectorEnableButtonSelectAll: String = "Выбрать все"
    override val historySelectorEnableButtonUnselectAll: String = "Снять выделение"
    override val historySelectorEnableButtonCancel: String = "Отмена"
    override val historyEmptyDescription: String = "История ваших примерок будет сохранена здесь"

    // Generation Result
    override val generationResultMoreTitle: String = "TODO"
    override val generationResultMoreSubtitle: String = "Больше образов для примерки"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Сделать фото"
    override val pickerSheetChooseLibrary: String = "Выбрать из библиотеки"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Ранее использованные фото"
    override val generatedOperationsSheetUploadNewButton: String = "+ Загрузить новое фото"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Пропустить"
    override val feedbackSheetSend: String = "Отправить"
    override val feedbackSheetSendFeedback: String = "Отправить отзыв"

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Разрешение использования камеры"
    override val dialogCameraPermissionDescription: String =
        "Пожалуйста, предоставьте доступ к камере в настройках приложения."
    override val dialogCameraPermissionConfirmButton: String = "Настройки"

    // General
    override val addToWish: String = "В избранное"
    override val addToCart: String = "Добавить в корзину"
    override val cancel: String = "Отмена"
    override val close: String = "TODO"
    override val tryOn: String = "Примерить"
    override val virtualTryOn: String = "TODO"
    override val share: String = "Поделиться"
    override val defaultErrorMessage: String =
        "Что-то пошло не так. Пожалуйста, повторите попытку позже"
}
