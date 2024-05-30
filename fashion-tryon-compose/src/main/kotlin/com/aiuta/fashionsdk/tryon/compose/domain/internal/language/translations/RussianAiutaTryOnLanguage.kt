package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.Plurals

internal object RussianAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "История"
    override val appBarSelect: String = "Выбрать"

    // Onboarding
    override val onboardingButtonNext: String = "Далее"
    override val onboardingButtonStart: String = "Начать"
    override val onboardingPageTryonTopic: String = "Примерьте перед покупкой"
    override val onboardingPageTryonSubtopic: String = "Просто загрузите своё фото и посмотрите, как это выглядит"
    override val onboardingPageBestResultTopic: String = "Для лучших результатов"
    override val onboardingPageBestResultSubtopic: String = "Используйте фото с хорошим освещением, стойте прямо на однотонном фоне"

    // Image selector
    override val imageSelectorUploadButton: String = "Загрузить своё фото"
    override val imageSelectorChangeButton: String = "Изменить фото"
    override val imageSelectorPoweredByAiuta1: String = "Работает благодаря "
    override val imageSelectorPoweredByAiuta2: String = "Aiuta"
    override val imageSelectorGeneratingOutfit: String = "Создание наряда"
    override val imageSelectorPhotos: Plurals =
        Plurals(
            one = "%d фото",
            other = "%d фотографии",
        )

    // History
    override val historySelectorDisabledButton: String = "Выбрать"
    override val historySelectorEnableButtonSelectAll: String = "Выбрать все"
    override val historySelectorEnableButtonUnselectAll: String = "Отменить выбор всех"
    override val historySelectorEnableButtonCancel: String = "Отмена"
    override val historyEmptyDescription: String = "После первой примерки ваша история примерок будет сохранена здесь"

    // Generation Result
    override val generationResultMoreTitle: String = "Больше вариантов для примерки"
    override val generationResultSwipeUp: String = "Проведите вверх для большего"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Сделать фото"
    override val pickerSheetChooseLibrary: String = "Выбрать из библиотеки"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Ранее использованные фото"
    override val generatedOperationsSheetUploadNewButton: String = "+ Загрузить новое фото"

    // General
    override val addToWish: String = "Добавить в любимое"
    override val addToCart: String = "Добавить в корзину"
    override val moreDetails: String = "Подробнее"
    override val aiutaUrl: String = "https://aiuta.com/"
    override val tryOn: String = "Примерить"
    override val share: String = "Поделиться"
}
