package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // App bar
    val appBarHistory: String
    val appBarSelect: String

    // Onboarding
    val onboardingButtonNext: String
    val onboardingButtonStart: String
    val onboardingPageTryonTopic: String
    val onboardingPageTryonSubtopic: String
    val onboardingPageBestResultTopic: String
    val onboardingPageBestResultSubtopic: String

    // Image selector
    val imageSelectorUploadButton: String
    val imageSelectorChangeButton: String
    val imageSelectorPoweredByAiuta1: String
    val imageSelectorPoweredByAiuta2: String
    val imageSelectorGeneratingOutfit: String
    val imageSelectorPhotos: Plurals

    // History
    val historySelectorDisabledButton: String
    val historySelectorEnableButtonSelectAll: String
    val historySelectorEnableButtonUnselectAll: String
    val historySelectorEnableButtonCancel: String
    val historyEmptyDescription: String

    // Generation Result
    val generationResultMoreTitle: String
    val generationResultSwipeUp: String

    // Bottom sheets
    // Picker sheet
    val pickerSheetTakePhoto: String
    val pickerSheetChooseLibrary: String

    // Generated operations sheet
    val generatedOperationsSheetPreviously: String
    val generatedOperationsSheetUploadNewButton: String

    // General
    val addToWish: String
    val addToCart: String
    val moreDetails: String
    val aiutaUrl: String
    val tryOn: String
    val share: String
}

internal class Plurals(
    val one: String,
    val other: String,
)
