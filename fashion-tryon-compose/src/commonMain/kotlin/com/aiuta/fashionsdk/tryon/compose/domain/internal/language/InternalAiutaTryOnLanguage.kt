package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.language.TranslationWord

@Immutable
internal interface InternalAiutaTryOnLanguage {
    // App bar
    val appBarHistory: String
    val appBarSelect: String

    // Image selector
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
    val modelSelect: String
    val tryOn: String
    val tryAgain: String
    val virtualTryOn: String
    val share: String
    val defaultErrorMessage: String
}
