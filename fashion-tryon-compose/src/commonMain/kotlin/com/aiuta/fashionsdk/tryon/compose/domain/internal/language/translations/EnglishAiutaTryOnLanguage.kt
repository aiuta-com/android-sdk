package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.configuration.language.TranslationWord
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class EnglishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "History"
    override val appBarSelect: String = "Select"

    // Image selector
    override val imageSelectorChangeButton: String = "Change photo"
    override val imageSelectorPoweredByAiuta: String = "Powered by Aiuta"
    override val imageSelectorProtectionPoint: String =
        "Your photos are protected and visible only to you"

    override val imageSelectorUploadingImage: String = "Uploading image"
    override val imageSelectorScanningBody: String = "Scanning your body"
    override val imageSelectorGeneratingOutfit: String = "Generating outfit"

    // Model selector
    override val modelSelectorCategories: List<TranslationWord> =
        listOf(
            TranslationWord(id = "woman", translation = "Woman"),
            TranslationWord(id = "man", translation = "Man"),
        )
    override val modelSelectorErrorEmptyModelsList: String = "The models list is empty"

    // History
    override val historySelectorEnableButtonSelectAll: String = "Select all"
    override val historySelectorEnableButtonUnselectAll: String = "Unselect all"
    override val historyEmptyDescription: String =
        "Once you try on first item your try-on history would be stored here"

    // Generation Result
    override val generationResultMoreTitle: String = "You might also like"
    override val generationResultMoreSubtitle: String = "More for you to try on"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Take a photo"
    override val pickerSheetChooseLibrary: String = "Choose from library"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Previously used photos"
    override val generatedOperationsSheetUploadNewButton: String = "+ New photo"
    override val generatedOperationsSheetUploadNewButtonWithModels: String = "+ New photo or model"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Skip"
    override val feedbackSheetSend: String = "Send"
    override val feedbackSheetSendFeedback: String = "Send feedback"

    // Disclaimer
    override val fitDisclaimerTitle: String = "Results may vary from real-life fit"
    override val fitDisclaimerBody: String = "Virtual try-on is a visualization tool that shows how items might look and may not perfectly represent how the item will fit in reality"

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Camera permission"
    override val dialogCameraPermissionDescription: String =
        "Please allow access to the camera in the application settings."
    override val dialogCameraPermissionConfirmButton: String = "Settings"

    // Invalid image
    override val dialogInvalidImageDescription: String = "We couldn’t detect anyone in this photo"

    // General
    override val addToWish: String = "Wishlist"
    override val addToCart: String = "Add to cart"
    override val cancel: String = "Cancel"
    override val close: String = "Close"
    override val modelSelect: String = "Select your model"
    override val tryOn: String = "Try on"
    override val tryAgain: String = "Try again"
    override val virtualTryOn: String = "Virtual Try-on"
    override val share: String = "Share"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}
