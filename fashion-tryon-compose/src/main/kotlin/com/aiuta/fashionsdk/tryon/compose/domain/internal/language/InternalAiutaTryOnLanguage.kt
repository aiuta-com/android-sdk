package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.TranslationWord

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // App bar
    val appBarHistory: String
    val appBarSelect: String
    val appBarModelSelect: String

    // Pre Onboarding
    val preOnboardingTitle: String
    val preOnboardingSubtitle: String
    val preOnboardingButton: String

    // Onboarding
    val onboardingButtonNext: String
    val onboardingButtonStart: String
    val onboardingPageTryonTopic: String
    val onboardingPageTryonSubtopic: String
    val onboardingPageTryonConsent: String
    val onboardingPageBestResultTopic: String
    val onboardingPageBestResultSubtopic: String
    val onboardingPageConsentTopic: String
    val onboardingPageConsentBody: String
    val onboardingPageConsentAgreePoint: String
    val onboardingPageConsentSupplementaryPoints: List<String>
    val onboardingAppbarTryonPage: String
    val onboardingAppbarBestResultPage: String
    val onboardingAppbarConsentPage: String
    val onboardingPageConsentFooter: String?

    // Image selector
    val imageSelectorUploadTitle: String
    val imageSelectorUploadSubtitle: String
    val imageSelectorUploadButton: String
    val imageSelectorSelectModelButton: String
    val imageSelectorOr: String
    val imageSelectorChangeButton: String

    val imageSelectorPoweredByAiuta: String
    val imageSelectorProtectionPoint: String

    val imageSelectorUploadingImage: String
    val imageSelectorScanningBody: String
    val imageSelectorGeneratingOutfit: String

    // Model selector
    val modelSelectorCategories: List<TranslationWord>
    val modelSelectorErrorEmptyModelsList: String

    // History
    val historySelectorEnableButtonSelectAll: String
    val historySelectorEnableButtonUnselectAll: String
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
    val generatedOperationsSheetUploadNewButtonWithModels: String

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
