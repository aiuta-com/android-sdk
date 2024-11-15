package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

internal class EnglishAiutaTryOnLanguage(
    brand: String,
    termsOfServiceUrl: String,
    privacyPolicyUrl: String,
) : InternalAiutaTryOnLanguage {
    // App bar
    override val appBarHistory: String = "History"
    override val appBarSelect: String = "Select"

    // Pre Onboarding
    override val preOnboardingTitle: String = "Try on you"
    override val preOnboardingSubtitle: String =
        "Welcome to our Virtual try-on.\nTry on the item directly on your photo"
    override val preOnboardingButton: String = "Let’s start"

    // Onboarding
    override val onboardingButtonNext: String = "Next"
    override val onboardingButtonStart: String = "Start"
    override val onboardingPageTryonTopic: String = "Try on before buying"
    override val onboardingPageTryonSubtopic: String =
        "Upload a photo and see how items look on you"
    override val onboardingPageBestResultTopic: String = "For best results"
    override val onboardingPageBestResultSubtopic: String =
        "Use a photo with good lighting, stand straight a plain background"
    override val onboardingPageConsentTopic: String = "Consent"
    override val onboardingPageConsentBody: String =
        "In order to try on items digitally, you agree to allow $brand to process your photo." +
            " Your data will be processed according to the $brand <b><a href=\"$privacyPolicyUrl\">Privacy Notice</a></b> " +
            "and <b><a href=\"$termsOfServiceUrl\">Terms of Use.</a></b>"
    override val onboardingPageConsentAgreePoint: String =
        "I agree to allow $brand to process my photo"
    override val onboardingPageConsentSupplementaryPoints: List<String> = emptyList()
    override val onboardingAppbarTryonPage: String = "<b>Step 1/3</b> - How it works"
    override val onboardingAppbarBestResultPage: String = "<b>Step 2/3</b> - For best result"
    override val onboardingAppbarConsentPage: String = "<b>Step 3/3</b> - Consent"

    // Image selector
    override val imageSelectorUploadButton: String = "Upload a photo of you"
    override val imageSelectorChangeButton: String = "Change photo"
    override val imageSelectorPoweredByAiuta: String = "Powered by Aiuta"
    override val imageSelectorProtectionPoint: String =
        "Your photos are protected and visible only to you"

    override val imageSelectorUploadingImage: String = "Uploading image"
    override val imageSelectorScanningBody: String = "Scanning your body"
    override val imageSelectorGeneratingOutfit: String = "Generating outfit"

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
    override val generatedOperationsSheetUploadNewButton: String = "+ Upload new photo"

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
    override val tryOn: String = "Try on"
    override val tryAgain: String = "Try again"
    override val virtualTryOn: String = "Virtual Try-on"
    override val share: String = "Share"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}
