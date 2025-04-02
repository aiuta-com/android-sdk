package com.aiuta.fashionsdk.tryon.compose.domain.internal.language

import com.aiuta.fashionsdk.tryon.compose.configuration.language.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.language.TranslationWord

public class CustomLanguage(
    // App bar
    override val appBarHistory: String,
    override val appBarSelect: String,
    // Image selector
    override val imageSelectorChangeButton: String,
    override val imageSelectorPoweredByAiuta: String,
    override val imageSelectorProtectionPoint: String,
    override val imageSelectorUploadingImage: String,
    override val imageSelectorScanningBody: String,
    override val imageSelectorGeneratingOutfit: String,
    override val modelSelectorCategories: List<TranslationWord>,
    override val modelSelectorErrorEmptyModelsList: String,
    // History
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    override val historyEmptyDescription: String,
    // Generation Result
    override val generationResultMoreTitle: String,
    override val generationResultMoreSubtitle: String,
    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String,
    override val pickerSheetChooseLibrary: String,
    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String,
    override val generatedOperationsSheetUploadNewButton: String,
    override val generatedOperationsSheetUploadNewButtonWithModels: String,
    // Feedback sheet
    override val feedbackSheetSkip: String,
    override val feedbackSheetSend: String,
    override val feedbackSheetSendFeedback: String,
    public val feedbackSheetTitle: String?,
    public val feedbackSheetOptions: List<String>?,
    public val feedbackSheetExtraOption: String?,
    public val feedbackSheetExtraOptionTitle: String?,
    public val feedbackSheetGratitude: String?,
    // Disclaimer
    override val fitDisclaimerTitle: String,
    override val fitDisclaimerBody: String,
    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String,
    override val dialogCameraPermissionDescription: String,
    override val dialogCameraPermissionConfirmButton: String,
    // Invalid image
    override val dialogInvalidImageDescription: String,
    // General
    override val addToWish: String,
    override val addToCart: String,
    override val cancel: String,
    override val close: String,
    override val modelSelect: String,
    override val tryOn: String,
    override val tryAgain: String,
    override val virtualTryOn: String,
    override val share: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage,
    InternalAiutaTryOnLanguage
