package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // App bar
    val appBarHistory: String
    val appBarSelect: String

    // Pre Onboarding
    val preOnboardingTitle: String
    val preOnboardingSubtitle: String
    val preOnboardingButton: String

    // Onboarding
    val onboardingButtonNext: String
    val onboardingButtonStart: String
    val onboardingPageTryonTopic: String
    val onboardingPageTryonSubtopic: String
    val onboardingPageBestResultTopic: String
    val onboardingPageBestResultSubtopic: String
    val onboardingPageConsentTopic: String
    val onboardingPageConsentBody: String
    val onboardingPageConsentAgreePoint: String
    val onboardingAppbarTryonPage: String
    val onboardingAppbarBestResultPage: String
    val onboardingAppbarConsentPage: String

    // Image selector
    val imageSelectorUploadButton: String
    val imageSelectorChangeButton: String

    val imageSelectorPoweredByAiuta: String
    val imageSelectorProtectionPoint: String

    val imageSelectorUploadingImage: String
    val imageSelectorScanningBody: String
    val imageSelectorGeneratingOutfit: String

    // History
    val historySelectorDisabledButton: String
    val historySelectorEnableButtonSelectAll: String
    val historySelectorEnableButtonUnselectAll: String
    val historySelectorEnableButtonCancel: String
    val historyEmptyDescription: String

    // Generation Result
    val generationResultMoreTitle: String
    val generationResultMoreSubtitle: String

    // Bottom sheets
    // Picker sheet
    val pickerSheetTakePhoto: String
    val pickerSheetChooseLibrary: String

    // Generated operations sheet
    val generatedOperationsSheetPreviously: String
    val generatedOperationsSheetUploadNewButton: String

    // Feedback sheet
    val feedbackSheetSkip: String
    val feedbackSheetSend: String
    val feedbackSheetSendFeedback: String

    // Disclaimer
    val fitDisclaimerTitle: String
    val fitDisclaimerBody: String

    // Dialog
    // Camera permission
    val dialogCameraPermissionTitle: String
    val dialogCameraPermissionDescription: String
    val dialogCameraPermissionConfirmButton: String

    // Invalid image
    val dialogInvalidImageDescription: String

    // General
    val addToWish: String
    val addToCart: String
    val cancel: String
    val close: String
    val tryOn: String
    val tryAgain: String
    val virtualTryOn: String
    val share: String
    val defaultErrorMessage: String
}

internal class Plurals(
    val one: String,
    val other: String,
)
