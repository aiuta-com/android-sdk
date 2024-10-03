package com.aiuta.fashionsdk.tryon.compose.domain.internal.language.translations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.Plurals

internal object EnglishAiutaTryOnLanguage : InternalAiutaTryOnLanguage {
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
    override val onboardingPageConsentBody: AnnotatedString =
        buildAnnotatedString {
            append(
                text =
                    "In order to try on items digitally, " +
                        "you agree to allow About You to process your photo. " +
                        "Your data will be processed according to the ",
            )
            append("Brand name ")
            withLink(
                LinkAnnotation.Url(
                    url = "https://aiuta.com/",
                    styles =
                        TextLinkStyles(
                            style = SpanStyle(textDecoration = TextDecoration.Underline),
                        ),
                ),
            ) {
                append("Terms of Use")
            }
        }
    override val onboardingPageConsentAgreePoint: String =
        "I agree to allow Brand name to\nprocess my photo"
    override val onboardingLegalDisclaimerBeforeClickable: String =
        "Your photo is processed as per the"
    override val onboardingLegalDisclaimerClickable: String = "Clarification Text"
    override val onboardingLegalDisclaimerAfterClickable: String = ""
    override val onboardingAppbarTryonPage: String = "Step 1/3 - How it works"
    override val onboardingAppbarBestResultPage: String = "Step 2/3 - For best result"
    override val onboardingAppbarConsentPage: String = "Step 3/3 - Consent"

    // Image selector
    override val imageSelectorUploadButton: String = "Upload a photo of you"
    override val imageSelectorChangeButton: String = "Change photo"
    override val imageSelectorPoweredByAiuta1: String = "Powered by "
    override val imageSelectorPoweredByAiuta2: String = "Aiuta"
    override val imageSelectorProtectionPoint: String = "Your photos are protected and visible only to you"
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
    override val historyEmptyDescription: String =
        "Once you try on first item your try-on history would be stored here"

    // Generation Result
    override val generationResultMoreTitle: String = "You might also like"
    override val generationResultMoreSubtitle: String = "More for you to try on"
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
    override val dialogCameraPermissionDescription: String =
        "Please allow access to the camera in the application settings."
    override val dialogCameraPermissionConfirmButton: String = "Settings"

    // General
    override val addToWish: String = "Add to wishlist"
    override val addToCart: String = "Add to cart"
    override val cancel: String = "Cancel"
    override val moreDetails: String = "More details"
    override val aiutaUrl: String = "https://aiuta.com/"
    override val tryOn: String = "Try on"
    override val virtualTryOn: String = "Virtual Try-on"
    override val share: String = "Share"
    override val defaultErrorMessage: String = "Something went wrong. Please try again later"
}
