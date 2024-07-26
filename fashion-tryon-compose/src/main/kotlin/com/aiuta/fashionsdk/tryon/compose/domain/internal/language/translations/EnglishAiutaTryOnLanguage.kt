package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.Plurals

internal object EnglishAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "History"
    override val appBarSelect: String = "Select"

    // Onboarding
    override val onboardingButtonNext: String = "Next"
    override val onboardingButtonStart: String = "Start"
    override val onboardingPageTryonTopic: String = "Try on before buying"
    override val onboardingPageTryonSubtopic: String = "Just upload your photo and see how it looks"
    override val onboardingPageBestResultTopic: String = "For best results"
    override val onboardingPageBestResultSubtopic: String = "Use a photo with good lighting, stand straight a plain background"

    // Image selector
    override val imageSelectorUploadButton: String = "Upload a photo of you"
    override val imageSelectorChangeButton: String = "Change photo"
    override val imageSelectorPoweredByAiuta1: String = "Powered by "
    override val imageSelectorPoweredByAiuta2: String = "Aiuta"
    override val imageSelectorGeneratingOutfit: String = "Generating outfit"
    override val imageSelectorPhotos: Plurals =
        Plurals(
            one = "%d photo",
            other = "%d photos",
        )

    // History
    override val historySelectorDisabledButton: String = "Select"
    override val historySelectorEnableButtonSelectAll: String = "Select all"
    override val historySelectorEnableButtonUnselectAll: String = "Unselect all"
    override val historySelectorEnableButtonCancel: String = "Cancel"
    override val historyEmptyDescription: String = "Once you try on first item your try-on history would be stored here"

    // Generation Result
    override val generationResultMoreTitle: String = "More for you to try on"
    override val generationResultSwipeUp: String = "Swipe up for more"

    // Bottom sheets
    // Picker sheet
    override val pickerSheetTakePhoto: String = "Take a photo"
    override val pickerSheetChooseLibrary: String = "Choose from library"

    // Generated operations sheet
    override val generatedOperationsSheetPreviously: String = "Previously used photos"
    override val generatedOperationsSheetUploadNewButton: String = "+ Upload new photo"

    // Feedback sheet
    override val feedbackSheetSkip: String = "Skip"
    override val feedbackSheetSend: String = "Send"
    override val feedbackSheetSendFeedback: String = "Send feedback"

    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String = "Camera permission"
    override val dialogCameraPermissionDescription: String = "Please allow access to the camera in the application settings."
    override val dialogCameraPermissionConfirmButton: String = "Settings"

    // General
    override val addToWish: String = "Add to wishlist"
    override val addToCart: String = "Add to cart"
    override val cancel: String = "Cancel"
    override val moreDetails: String = "More details"
    override val aiutaUrl: String = "https://aiuta.com/"
    override val tryOn: String = "Try on"
    override val share: String = "Share"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}
