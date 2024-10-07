package com.aiuta.fashionsdk.tryon.compose.domain.models

import com.aiuta.fashionsdk.tryon.compose.domain.internal.language.InternalAiutaTryOnLanguage

/**
 * One of the languages supported by AiutaTryOn flow
 */
public sealed interface AiutaTryOnLanguage {
    // Code in ISO-639
    public val code: String
}

public object EnglishLanguage : AiutaTryOnLanguage {
    override val code: String = "en"
}

public object TurkishLanguage : AiutaTryOnLanguage {
    override val code: String = "tr"
}

public object RussianLanguage : AiutaTryOnLanguage {
    override val code: String = "ru"
}

public class CustomLanguage(
    // App bar
    override val appBarHistory: String,
    override val appBarSelect: String,
    // Pre Onboarding
    override val preOnboardingTitle: String,
    override val preOnboardingSubtitle: String,
    override val preOnboardingButton: String,
    // Onboarding
    override val onboardingButtonNext: String,
    override val onboardingButtonStart: String,
    override val onboardingPageTryonTopic: String,
    override val onboardingPageTryonSubtopic: String,
    override val onboardingPageBestResultTopic: String,
    override val onboardingPageBestResultSubtopic: String,
    override val onboardingPageConsentTopic: String,
    override val onboardingPageConsentBody: String,
    override val onboardingPageConsentAgreePoint: String,
    override val onboardingAppbarTryonPage: String,
    override val onboardingAppbarBestResultPage: String,
    override val onboardingAppbarConsentPage: String,
    // Image selector
    override val imageSelectorUploadButton: String,
    override val imageSelectorChangeButton: String,
    override val imageSelectorPoweredByAiuta: String,
    override val imageSelectorProtectionPoint: String,
    override val imageSelectorGeneratingOutfit: String,
    // History
    override val historySelectorDisabledButton: String,
    override val historySelectorEnableButtonSelectAll: String,
    override val historySelectorEnableButtonUnselectAll: String,
    override val historySelectorEnableButtonCancel: String,
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
    // Feedback sheet
    override val feedbackSheetSkip: String,
    override val feedbackSheetSend: String,
    override val feedbackSheetSendFeedback: String,
    // Dialog
    // Camera permission
    override val dialogCameraPermissionTitle: String,
    override val dialogCameraPermissionDescription: String,
    override val dialogCameraPermissionConfirmButton: String,
    // General
    override val addToWish: String,
    override val addToCart: String,
    override val cancel: String,
    override val close: String,
    override val tryOn: String,
    override val virtualTryOn: String,
    override val share: String,
    override val defaultErrorMessage: String,
) : AiutaTryOnLanguage, InternalAiutaTryOnLanguage {
    override val code: String = error("Custom language don't handle code")

    init {
        // App bar
        appBarHistory.atMost(22)
        appBarSelect.atMost(11)
        // Pre Onboarding
        preOnboardingTitle.atMost(16)
        preOnboardingSubtitle.atMost(70)
        preOnboardingButton.atMost(20)
        // Onboarding
        onboardingButtonNext.atMost(20)
        onboardingButtonStart.atMost(20)
        onboardingPageTryonTopic.atMost(26)
        onboardingPageTryonSubtopic.atMost(64)
        onboardingPageBestResultTopic.atMost(26)
        onboardingPageBestResultSubtopic.atMost(64)
        onboardingPageConsentTopic.atMost(30)
        onboardingPageConsentBody.atMost(320)
        onboardingPageConsentAgreePoint.atMost(64)
        onboardingAppbarTryonPage.atMost(30)
        onboardingAppbarBestResultPage.atMost(30)
        onboardingAppbarConsentPage.atMost(30)
        // Image selector
        imageSelectorUploadButton.atMost(20)
        imageSelectorChangeButton.atMost(24)
        imageSelectorPoweredByAiuta.atMost(34)
        imageSelectorProtectionPoint.atMost(60)
        imageSelectorGeneratingOutfit.atMost(22)
        // History
        historySelectorDisabledButton.atMost(11)
        historySelectorEnableButtonSelectAll.atMost(16)
        historySelectorEnableButtonUnselectAll.atMost(16)
        historySelectorEnableButtonCancel.atMost(10)
        historyEmptyDescription.atMost(80)
        // Generation Result
        generationResultMoreTitle.atMost(30)
        generationResultMoreSubtitle.atMost(30)
        // Bottom sheets
        // Picker sheet
        pickerSheetTakePhoto.atMost(26)
        pickerSheetChooseLibrary.atMost(26)
        // Generated operations sheet
        generatedOperationsSheetPreviously.atMost(30)
        generatedOperationsSheetUploadNewButton.atMost(30)
        // Feedback sheet
        feedbackSheetSkip.atMost(30)
        feedbackSheetSend.atMost(20)
        feedbackSheetSendFeedback.atMost(20)
        // Dialog
        // Camera permission
        dialogCameraPermissionTitle.atMost(20)
        dialogCameraPermissionDescription.atMost(100)
        dialogCameraPermissionConfirmButton.atMost(20)
        // General
        addToWish.atMost(16)
        addToCart.atMost(20)
        cancel.atMost(10)
        close.atMost(10)
        tryOn.atMost(20)
        virtualTryOn.atMost(22)
        share.atMost(20)
        defaultErrorMessage.atMost(60)
    }

    private fun String.atMost(maxNumber: Int) {
        check(this.length <= maxNumber) {
            "String \"$this\" contains more than max number of chars - ${this.length} > $maxNumber"
        }
    }
}

/**
 * Default language for AiutaTryOn flow
 */
internal val defaultAiutaTryOnLanguage: AiutaTryOnLanguage = EnglishLanguage
