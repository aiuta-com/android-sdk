package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class RussianAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "История"
    override val appBarSelect: String = "Выбрать"

    // Pre Onboarding
    override val preOnboardingTitle: String = "Примерьте на себе"
    override val preOnboardingSubtitle: String =
        "Добро пожаловать в виртуальную примерочную.\nПримерьте вещь прямо на своём фото"
    override val preOnboardingButton: String = "Начать"

    // Onboarding
    override val onboardingButtonNext: String = "Далее"
    override val onboardingButtonStart: String = "Начать"
    override val onboardingPageTryonTopic: String = "Примерьте перед покупкой"
    override val onboardingPageTryonSubtopic: String = "Загрузите своё фото и порадуйтесь новому образу"
    override val onboardingPageBestResultTopic: String = "Для лучшего качества примерки"
    override val onboardingPageBestResultSubtopic: String = "Используйте фото с хорошим освещением и прямой позой"
    override val onboardingPageConsentTopic: String = "Согласие"
    override val onboardingPageConsentBody: String =
        "Чтобы примерить товары виртуально, вы соглашаетесь на обработку вашего фото c $brand." +
            " Ваши данные будут обработаны в соответствии с <b><a href=\"$privacyPolicyUrl\">Политикой конфиденциальности</a></b> " +
            "и <b><a href=\"$termsOfServiceUrl\">Условиями использования.</a></b>"
    override val onboardingPageConsentAgreePoint: String =
        "Я соглашаюсь на обработку моего фото c $brand"
    override val onboardingAppbarTryonPage: String = "<b>Шаг 1/3</b> - Как это работает"
    override val onboardingAppbarBestResultPage: String = "<b>Шаг 2/3</b> - Лучшие результаты"
    override val onboardingAppbarConsentPage: String = "<b>Шаг 3/3</b> - Согласие"

    // Image selector
    override val imageSelectorUploadButton: String = "Загрузить своё фото"
    override val imageSelectorChangeButton: String = "Заменить фото"
    override val imageSelectorPoweredByAiuta: String = "Powered by Aiuta"
    override val imageSelectorProtectionPoint: String = "Ваши фото защищены и видны только вам"

    override val imageSelectorUploadingImage: String = "Загружаем фото"
    override val imageSelectorScanningBody: String = "Сканируем"
    override val imageSelectorGeneratingOutfit: String = "Генерируем образ"

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
    override val tryOn: String = "Примерить"
    override val tryAgain: String = "Попробовать снова"
    override val virtualTryOn: String = "Виртуальная примерка"
    override val share: String = "Поделиться"
    override val defaultErrorMessage: String =
        "Что-то пошло не так. Пожалуйста, повторите попытку позже"
}
