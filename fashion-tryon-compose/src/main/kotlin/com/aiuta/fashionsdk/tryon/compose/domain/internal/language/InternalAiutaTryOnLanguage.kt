package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.AnnotatedString

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
    val onboardingPageConsentBody: AnnotatedString
    val onboardingPageConsentAgreePoint: String
    val onboardingLegalDisclaimerBeforeClickable: String
    val onboardingLegalDisclaimerClickable: String
    val onboardingLegalDisclaimerAfterClickable: String

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

    // Feedback sheet
    val feedbackSheetSkip: String
    val feedbackSheetSend: String
    val feedbackSheetSendFeedback: String

    // Dialog
    // Camera permission
    val dialogCameraPermissionTitle: String
    val dialogCameraPermissionDescription: String
    val dialogCameraPermissionConfirmButton: String

    // General
    val addToWish: String
    val addToCart: String
    val cancel: String
    val moreDetails: String
    val aiutaUrl: String
    val tryOn: String
    val share: String
    val defaultErrorMessage: String
}

internal class Plurals(
    val one: String,
    val other: String,
)
